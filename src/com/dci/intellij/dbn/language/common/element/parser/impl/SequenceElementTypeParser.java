package com.dci.intellij.dbn.language.common.element.parser.impl;

import com.dci.intellij.dbn.language.common.ParseException;
import com.dci.intellij.dbn.language.common.TokenType;
import com.dci.intellij.dbn.language.common.element.BlockElementType;
import com.dci.intellij.dbn.language.common.element.ElementType;
import com.dci.intellij.dbn.language.common.element.IdentifierElementType;
import com.dci.intellij.dbn.language.common.element.IterationElementType;
import com.dci.intellij.dbn.language.common.element.SequenceElementType;
import com.dci.intellij.dbn.language.common.element.impl.WrappingDefinition;
import com.dci.intellij.dbn.language.common.element.parser.AbstractElementTypeParser;
import com.dci.intellij.dbn.language.common.element.parser.ParseResult;
import com.dci.intellij.dbn.language.common.element.parser.ParseResultType;
import com.dci.intellij.dbn.language.common.element.parser.ParserBuilder;
import com.dci.intellij.dbn.language.common.element.parser.ParserContext;
import com.dci.intellij.dbn.language.common.element.path.ParsePathNode;
import com.dci.intellij.dbn.language.common.element.util.ElementTypeAttribute;
import com.dci.intellij.dbn.language.common.element.util.ParseBuilderErrorHandler;
import com.intellij.lang.PsiBuilder;

public class SequenceElementTypeParser<ET extends SequenceElementType> extends AbstractElementTypeParser<ET> {
    public SequenceElementTypeParser(ET elementType) {
        super(elementType);
    }

    public ParseResult parse(ParsePathNode parentNode, boolean optional, int depth, ParserContext context) throws ParseException {
        ParserBuilder builder = context.getBuilder();
        logBegin(builder, optional, depth);
        SequenceElementType elementType = getElementType();
        ParsePathNode node = createParseNode(parentNode, builder.getCurrentOffset());

        WrappingDefinition wrapping = elementType.getWrapping();
        if (wrapping != null) {
            while(builder.getTokenType() == wrapping.getBeginElementType().getTokenType()) {
                builder.advanceLexer(node, true);
            }
        }

        PsiBuilder.Marker marker = builder.mark();
        int matches = 0;
        int matchedTokens = 0;

        TokenType tokenType = builder.getTokenType();
        boolean isDummyToken = isDummyToken(builder.getTokenText());
        boolean isSuppressibleReservedWord =
                tokenType instanceof TokenType &&
                !elementType.is(ElementTypeAttribute.STATEMENT) &&
                isSuppressibleReservedWord(tokenType, node);


        if (tokenType != null && !tokenType.isChameleon() && (isDummyToken || isSuppressibleReservedWord || elementType.getLookupCache().canStartWithToken(tokenType))) {
            ElementType[] elementTypes = elementType.getElementTypes();
            for (int i = 0; i < elementTypes.length; i++) {
                // is end of document
                if (tokenType == null || tokenType.isChameleon()) {
                    ParseResultType resultType =
                            elementType.isOptional(i) && (elementType.isLast(i) || elementType.isOptionalFromIndex(i)) ? ParseResultType.FULL_MATCH :
                            !elementType.isFirst(i) && !elementType.isOptionalFromIndex(i) && !elementType.isExitIndex(i) ? ParseResultType.PARTIAL_MATCH : ParseResultType.NO_MATCH;
                    return stepOut(marker, depth, resultType, matchedTokens, node, context);
                }

                ParseResult result = ParseResult.createNoMatchResult();
                // current token can still be part of the iterated element.
                //if (elementTypes[i].containsToken(tokenType)) {
                if (isDummyToken || elementTypes[i].getLookupCache().canStartWithToken(tokenType) || isSuppressibleReservedWord(tokenType, node)) {

                    // /ParseNode pathNode = new ParseNode(parentPath, this, i, builder.getCurrentOffset());
                    node = node.createVariant(builder.getCurrentOffset(), i);
                    result = elementTypes[i].getParser().parse(node, elementType.isOptional(i), depth + 1, context);

                    if (result.isMatch()) {
                        matchedTokens = matchedTokens + result.getMatchedTokens();
                        tokenType = builder.getTokenType();
                        isDummyToken = isDummyToken(builder.getTokenText());
                        matches++;
                    }
                }

                // not matched and not optional
                if (result.isNoMatch() && !elementType.isOptional(i)) {
                    boolean isWeakMatch = matches < 2 && matchedTokens < 3 && i > 1 && ignoreFirstMatch();
                    
                    if (elementType.isFirst(i) || elementType.isExitIndex(i) || isWeakMatch || matches == 0) {
                        //if (isFirst(i) || isExitIndex(i)) {
                        return stepOut(marker, depth, ParseResultType.NO_MATCH, matchedTokens, node, context);
                    }

                    int offset = advanceLexerToNextLandmark(node, context);

                    // no landmarks found or landmark in parent found
                    if (offset == 0 || offset < 0) {
                        /*if (offset == i) {
                            elementTypes[i].parse(node, builder, isOptional(i), depth + 1, timestamp);
                        }*/
                        return stepOut(marker, depth, ParseResultType.PARTIAL_MATCH, matchedTokens, node, context);
                    }


                    tokenType = builder.getTokenType();
                    isDummyToken = isDummyToken(builder.getTokenText());
                    // local landmarks found
                    if (offset > 0) {
                        i = offset - 1;
                        continue;
                    }
                }

                // if is last element
                if (elementType.isLast(i)) {
                    //matches == 0 reaches this stage only if all sequence elements are optional
                    ParseResultType resultType = matches == 0 ? ParseResultType.NO_MATCH : ParseResultType.FULL_MATCH;
                    return stepOut(marker, depth, resultType, matchedTokens, node, context);
                }
            }
        }

        return stepOut(marker, depth, ParseResultType.NO_MATCH, matchedTokens, node, context);
    }

    private boolean ignoreFirstMatch() {
        ElementType firstElementType = getElementType().getElementTypes()[0];
        if (firstElementType instanceof IdentifierElementType) {
            IdentifierElementType identifierElementType = (IdentifierElementType) firstElementType;
            return !identifierElementType.isDefinition();
        }
        return false;
    }

    protected ParseResult stepOut(PsiBuilder.Marker marker, int depth, ParseResultType resultType, int matchedTokens, ParsePathNode node, ParserContext context) {
        ParserBuilder builder = context.getBuilder();
        if (resultType == ParseResultType.NO_MATCH) {
            builder.markerRollbackTo(marker);
        } else {
            if (getElementType() instanceof BlockElementType)
                builder.markerDrop(marker); else
                builder.markerDone(marker, getElementType());
        }

        WrappingDefinition wrapping = getElementType().getWrapping();
        if (wrapping != null) {
            while(builder.getTokenType() == wrapping.getEndElementType().getTokenType()) {
                builder.advanceLexer(node, true);
            }
        }

        return super.stepOut(null, depth, resultType, matchedTokens, node, context);
    }    

    private int advanceLexerToNextLandmark(ParsePathNode node, ParserContext context) {
        int index = node.getCurrentSiblingPosition();
        ParserBuilder builder = context.getBuilder();
        PsiBuilder.Marker marker = builder.mark();
        SequenceElementType elementType = getElementType();
        ParseBuilderErrorHandler.updateBuilderError(elementType.getFirstPossibleTokensFromIndex(index), context);

        if (!builder.eof()) {
            TokenType tokenType = builder.getTokenType();
            int newIndex = getLandmarkIndex(tokenType, index, node);
            if (newIndex == index) {
                builder.advanceLexer(node);
            }
        }

        while (!builder.eof()) {
            TokenType tokenType = builder.getTokenType();
            if (tokenType != null) {
                int newIndex = getLandmarkIndex(tokenType, index, node);

                // no landmark hit -> spool the builder
                if (newIndex == 0) {
                    builder.advanceLexer(node);
                } else {
                    builder.markerDone(marker, getElementBundle().getUnknownElementType());
                    return newIndex;
                }
            }
        }
        builder.markerDone(marker, getElementBundle().getUnknownElementType());
        return 0;
    }

    protected int getLandmarkIndex(TokenType tokenType, int index, ParsePathNode parentParseNode) {
        if (tokenType.isParserLandmark()) {
            ElementType[] elementTypes = getElementType().getElementTypes();
            for (int i=index; i< elementTypes.length; i++) {
                // check children landmarks
                if (elementTypes[i].getLookupCache().canStartWithToken(tokenType)) {
                    return i;
                }
            }

            ParsePathNode parseNode = parentParseNode;
            while (parseNode != null) {
                if (parseNode.getElementType() instanceof SequenceElementType) {
                    SequenceElementType sequenceElementType = (SequenceElementType) parseNode.getElementType();
                    if ( sequenceElementType.containsLandmarkTokenFromIndex(tokenType, parseNode.getCurrentSiblingPosition() + 1)) {
                        return -1;
                    }
                }
                if (parseNode.getElementType() instanceof IterationElementType) {
                    IterationElementType iterationElementType = (IterationElementType) parseNode.getElementType();
                    if (iterationElementType.isSeparator(tokenType)) {
                        return -1;
                    }
                }
                parseNode = parseNode.getParent();
            }
        }
        return 0;
    }


}