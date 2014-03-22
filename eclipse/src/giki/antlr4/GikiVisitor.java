// Generated from C:\Users\Jakob\Dropbox\Eclipse\workspace\Giki 0.0.4\src\giki\antlr4\Giki.g4 by ANTLR 4.1
package giki.antlr4;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GikiParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GikiVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GikiParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull GikiParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#produceString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduceString(@NotNull GikiParser.ProduceStringContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#termValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTermValue(@NotNull GikiParser.TermValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#interval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval(@NotNull GikiParser.IntervalContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#next}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNext(@NotNull GikiParser.NextContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#terms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerms(@NotNull GikiParser.TermsContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#moduleModifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleModifiers(@NotNull GikiParser.ModuleModifiersContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#pipelineTo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipelineTo(@NotNull GikiParser.PipelineToContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#consumeString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConsumeString(@NotNull GikiParser.ConsumeStringContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#decision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecision(@NotNull GikiParser.DecisionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(@NotNull GikiParser.VariableDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#matchID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchID(@NotNull GikiParser.MatchIDContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#namedPrimitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedPrimitive(@NotNull GikiParser.NamedPrimitiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#produceInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduceInteger(@NotNull GikiParser.ProduceIntegerContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#quote}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuote(@NotNull GikiParser.QuoteContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#consumeInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConsumeInteger(@NotNull GikiParser.ConsumeIntegerContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap(@NotNull GikiParser.MapContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#decisionTail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecisionTail(@NotNull GikiParser.DecisionTailContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#compose}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompose(@NotNull GikiParser.ComposeContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#peek}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPeek(@NotNull GikiParser.PeekContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#pipelineInter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipelineInter(@NotNull GikiParser.PipelineInterContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#move}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove(@NotNull GikiParser.MoveContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#preFixRest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreFixRest(@NotNull GikiParser.PreFixRestContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(@NotNull GikiParser.ModuleContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#pipelineFrom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipelineFrom(@NotNull GikiParser.PipelineFromContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#slotSet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSlotSet(@NotNull GikiParser.SlotSetContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#lookupChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLookupChain(@NotNull GikiParser.LookupChainContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(@NotNull GikiParser.LabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(@NotNull GikiParser.ListContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#preFixOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreFixOperation(@NotNull GikiParser.PreFixOperationContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#produceVerbatim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProduceVerbatim(@NotNull GikiParser.ProduceVerbatimContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(@NotNull GikiParser.ExpressionsContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(@NotNull GikiParser.AssignmentContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(@NotNull GikiParser.TermContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#pipeline}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeline(@NotNull GikiParser.PipelineContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull GikiParser.ProgramContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#preFixFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreFixFile(@NotNull GikiParser.PreFixFileContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#ioOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIoOperation(@NotNull GikiParser.IoOperationContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#group}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(@NotNull GikiParser.GroupContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(@NotNull GikiParser.IdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link GikiParser#programElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramElements(@NotNull GikiParser.ProgramElementsContext ctx);
}