// Generated from C:\github\giki-lang\eclipse\src\giki\antlr4\Giki.g4 by ANTLR 4.1
package giki.antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GikiParser}.
 */
public interface GikiListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GikiParser#decisionTail}.
	 * @param ctx the parse tree
	 */
	void enterDecisionTail(@NotNull GikiParser.DecisionTailContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#decisionTail}.
	 * @param ctx the parse tree
	 */
	void exitDecisionTail(@NotNull GikiParser.DecisionTailContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#next}.
	 * @param ctx the parse tree
	 */
	void enterNext(@NotNull GikiParser.NextContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#next}.
	 * @param ctx the parse tree
	 */
	void exitNext(@NotNull GikiParser.NextContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#preFixFile}.
	 * @param ctx the parse tree
	 */
	void enterPreFixFile(@NotNull GikiParser.PreFixFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#preFixFile}.
	 * @param ctx the parse tree
	 */
	void exitPreFixFile(@NotNull GikiParser.PreFixFileContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#consumeString}.
	 * @param ctx the parse tree
	 */
	void enterConsumeString(@NotNull GikiParser.ConsumeStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#consumeString}.
	 * @param ctx the parse tree
	 */
	void exitConsumeString(@NotNull GikiParser.ConsumeStringContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull GikiParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull GikiParser.ProgramContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#slotSet}.
	 * @param ctx the parse tree
	 */
	void enterSlotSet(@NotNull GikiParser.SlotSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#slotSet}.
	 * @param ctx the parse tree
	 */
	void exitSlotSet(@NotNull GikiParser.SlotSetContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#produceVerbatim}.
	 * @param ctx the parse tree
	 */
	void enterProduceVerbatim(@NotNull GikiParser.ProduceVerbatimContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#produceVerbatim}.
	 * @param ctx the parse tree
	 */
	void exitProduceVerbatim(@NotNull GikiParser.ProduceVerbatimContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#ioOperation}.
	 * @param ctx the parse tree
	 */
	void enterIoOperation(@NotNull GikiParser.IoOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#ioOperation}.
	 * @param ctx the parse tree
	 */
	void exitIoOperation(@NotNull GikiParser.IoOperationContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#quote}.
	 * @param ctx the parse tree
	 */
	void enterQuote(@NotNull GikiParser.QuoteContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#quote}.
	 * @param ctx the parse tree
	 */
	void exitQuote(@NotNull GikiParser.QuoteContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#terms}.
	 * @param ctx the parse tree
	 */
	void enterTerms(@NotNull GikiParser.TermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#terms}.
	 * @param ctx the parse tree
	 */
	void exitTerms(@NotNull GikiParser.TermsContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#pipelineFrom}.
	 * @param ctx the parse tree
	 */
	void enterPipelineFrom(@NotNull GikiParser.PipelineFromContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#pipelineFrom}.
	 * @param ctx the parse tree
	 */
	void exitPipelineFrom(@NotNull GikiParser.PipelineFromContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull GikiParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull GikiParser.TermContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#preFixOperation}.
	 * @param ctx the parse tree
	 */
	void enterPreFixOperation(@NotNull GikiParser.PreFixOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#preFixOperation}.
	 * @param ctx the parse tree
	 */
	void exitPreFixOperation(@NotNull GikiParser.PreFixOperationContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(@NotNull GikiParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(@NotNull GikiParser.MapContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#group}.
	 * @param ctx the parse tree
	 */
	void enterGroup(@NotNull GikiParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#group}.
	 * @param ctx the parse tree
	 */
	void exitGroup(@NotNull GikiParser.GroupContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(@NotNull GikiParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(@NotNull GikiParser.IdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#move}.
	 * @param ctx the parse tree
	 */
	void enterMove(@NotNull GikiParser.MoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#move}.
	 * @param ctx the parse tree
	 */
	void exitMove(@NotNull GikiParser.MoveContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull GikiParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull GikiParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#produceString}.
	 * @param ctx the parse tree
	 */
	void enterProduceString(@NotNull GikiParser.ProduceStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#produceString}.
	 * @param ctx the parse tree
	 */
	void exitProduceString(@NotNull GikiParser.ProduceStringContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#decision}.
	 * @param ctx the parse tree
	 */
	void enterDecision(@NotNull GikiParser.DecisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#decision}.
	 * @param ctx the parse tree
	 */
	void exitDecision(@NotNull GikiParser.DecisionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(@NotNull GikiParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(@NotNull GikiParser.AssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModule(@NotNull GikiParser.ModuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModule(@NotNull GikiParser.ModuleContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#pipelineInter}.
	 * @param ctx the parse tree
	 */
	void enterPipelineInter(@NotNull GikiParser.PipelineInterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#pipelineInter}.
	 * @param ctx the parse tree
	 */
	void exitPipelineInter(@NotNull GikiParser.PipelineInterContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(@NotNull GikiParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(@NotNull GikiParser.LabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#termValue}.
	 * @param ctx the parse tree
	 */
	void enterTermValue(@NotNull GikiParser.TermValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#termValue}.
	 * @param ctx the parse tree
	 */
	void exitTermValue(@NotNull GikiParser.TermValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull GikiParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull GikiParser.ListContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(@NotNull GikiParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(@NotNull GikiParser.ExpressionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#programElements}.
	 * @param ctx the parse tree
	 */
	void enterProgramElements(@NotNull GikiParser.ProgramElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#programElements}.
	 * @param ctx the parse tree
	 */
	void exitProgramElements(@NotNull GikiParser.ProgramElementsContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(@NotNull GikiParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(@NotNull GikiParser.VariableDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#pipelineTo}.
	 * @param ctx the parse tree
	 */
	void enterPipelineTo(@NotNull GikiParser.PipelineToContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#pipelineTo}.
	 * @param ctx the parse tree
	 */
	void exitPipelineTo(@NotNull GikiParser.PipelineToContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#peek}.
	 * @param ctx the parse tree
	 */
	void enterPeek(@NotNull GikiParser.PeekContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#peek}.
	 * @param ctx the parse tree
	 */
	void exitPeek(@NotNull GikiParser.PeekContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#pipeline}.
	 * @param ctx the parse tree
	 */
	void enterPipeline(@NotNull GikiParser.PipelineContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#pipeline}.
	 * @param ctx the parse tree
	 */
	void exitPipeline(@NotNull GikiParser.PipelineContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#lookupChain}.
	 * @param ctx the parse tree
	 */
	void enterLookupChain(@NotNull GikiParser.LookupChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#lookupChain}.
	 * @param ctx the parse tree
	 */
	void exitLookupChain(@NotNull GikiParser.LookupChainContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#preFixRest}.
	 * @param ctx the parse tree
	 */
	void enterPreFixRest(@NotNull GikiParser.PreFixRestContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#preFixRest}.
	 * @param ctx the parse tree
	 */
	void exitPreFixRest(@NotNull GikiParser.PreFixRestContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#interpolation}.
	 * @param ctx the parse tree
	 */
	void enterInterpolation(@NotNull GikiParser.InterpolationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#interpolation}.
	 * @param ctx the parse tree
	 */
	void exitInterpolation(@NotNull GikiParser.InterpolationContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#produceInteger}.
	 * @param ctx the parse tree
	 */
	void enterProduceInteger(@NotNull GikiParser.ProduceIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#produceInteger}.
	 * @param ctx the parse tree
	 */
	void exitProduceInteger(@NotNull GikiParser.ProduceIntegerContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#compose}.
	 * @param ctx the parse tree
	 */
	void enterCompose(@NotNull GikiParser.ComposeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#compose}.
	 * @param ctx the parse tree
	 */
	void exitCompose(@NotNull GikiParser.ComposeContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(@NotNull GikiParser.IntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(@NotNull GikiParser.IntervalContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#namedPrimitive}.
	 * @param ctx the parse tree
	 */
	void enterNamedPrimitive(@NotNull GikiParser.NamedPrimitiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#namedPrimitive}.
	 * @param ctx the parse tree
	 */
	void exitNamedPrimitive(@NotNull GikiParser.NamedPrimitiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#consumeInteger}.
	 * @param ctx the parse tree
	 */
	void enterConsumeInteger(@NotNull GikiParser.ConsumeIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#consumeInteger}.
	 * @param ctx the parse tree
	 */
	void exitConsumeInteger(@NotNull GikiParser.ConsumeIntegerContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#moduleModifiers}.
	 * @param ctx the parse tree
	 */
	void enterModuleModifiers(@NotNull GikiParser.ModuleModifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#moduleModifiers}.
	 * @param ctx the parse tree
	 */
	void exitModuleModifiers(@NotNull GikiParser.ModuleModifiersContext ctx);

	/**
	 * Enter a parse tree produced by {@link GikiParser#matchID}.
	 * @param ctx the parse tree
	 */
	void enterMatchID(@NotNull GikiParser.MatchIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link GikiParser#matchID}.
	 * @param ctx the parse tree
	 */
	void exitMatchID(@NotNull GikiParser.MatchIDContext ctx);
}