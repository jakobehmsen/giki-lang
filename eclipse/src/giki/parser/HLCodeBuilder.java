package giki.parser;

import giki.runtime.CodeBuilder;
import giki.runtime.Container;
import giki.runtime.Symbol;
import giki.runtime.Symbol.Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.Callable;

public interface HLCodeBuilder {
	public static class Validation {
		private HashSet<String> variables = new HashSet<String>();
//		private ArrayList<HLIdentifier> dependencies = new ArrayList<HLIdentifier>();

		public void declareVariable(String identifier) {
			variables.add(identifier);
		}

		public boolean isVariable(String identifier) {
			return variables.contains(identifier);
		}

//		public void addUnresolvedIdentifier(HLIdentifier identifier) {
//			dependencies.add(identifier);
//		}
//		
//		public ArrayList<HLIdentifier> getDependencies() {
//			return dependencies;
//		}
	}
	
	public static class Factory {
		private static final Hashtable<Symbol.Identifier, Func1<HLCodeBuilder, Symbol.Map>> astTypeToFactoryMap = createFactoryMap();
		
		private static Hashtable<Symbol.Identifier, Func1<HLCodeBuilder, Symbol.Map>> createFactoryMap() {
			Hashtable<Symbol.Identifier, Func1<HLCodeBuilder, Symbol.Map>> factoryMap = new Hashtable<Symbol.Identifier, Func1<HLCodeBuilder, Symbol.Map>>();
			
			factoryMap.put(Symbol.Map.AST_TYPE_ASSIGNMENT, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					String identifier = ast.getAsString(Symbol.Map.KEY_IDENTIFIER);
					HLCodeBuilder value = fromAST(ast.get(Symbol.Map.KEY_VALUE));
					return new HLVariableAssignment(identifier, value);
				}
			});
			
			factoryMap.put(Symbol.Map.AST_TYPE_VARIABLE_DECLARATION, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					String identifier = ast.getAsString(Symbol.Map.KEY_IDENTIFIER);
					HLCodeBuilder initialization = fromAST(ast.get(Symbol.Map.KEY_INITIALIZATION));
					return new HLVariableDeclaration(identifier, initialization);
				}
			});
			
			factoryMap.put(Symbol.Map.AST_TYPE_INSTRUCTION, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					int opcode = ast.getAsInt(Symbol.Map.KEY_OPCODE);
					Symbol operandSymbol = ast.get(Symbol.Map.KEY_OPERAND);
					Object operand;
					
//					if(operandSymbol instanceof Symbol.Integer)
//						operand = ((Symbol.Integer)operandSymbol).value;
//					else
						operand = operandSymbol;
					
					return new HLInstruction(opcode, operand);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_NOT, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					HLCodeBuilder body = fromAST(ast.get(Symbol.Map.KEY_BODY));
					return new HLNot(body);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_LOOP, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					HLCodeBuilder body = fromAST(ast.get(Symbol.Map.KEY_BODY));
					return new HLLoop(body);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_GROUP, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					ArrayList<HLCodeBuilder> items = hlCodeBuildersFromAST(ast.getAsDefaultContainer(Symbol.Map.KEY_ITEMS));
					return new HLSequence(items);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_PIPELINE, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					ArrayList<HLCodeBuilder> pipes = hlCodeBuildersFromAST(ast.getAsDefaultContainer(Symbol.Map.KEY_PIPES));
					return new HLPipeline(pipes);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_TO, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					HLCodeBuilder target = fromAST(ast.get(Symbol.Map.KEY_TARGET));
					HLCodeBuilder body = fromAST(ast.get(Symbol.Map.KEY_BODY));
					return new HLTo(target, body);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_FROM, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					HLCodeBuilder source = fromAST(ast.get(Symbol.Map.KEY_SOURCE));
					HLCodeBuilder body = fromAST(ast.get(Symbol.Map.KEY_BODY));
					return new HLFrom(source, body);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_IO_SCOPE, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					HLCodeBuilder body = fromAST(ast.get(Symbol.Map.KEY_BODY));
					return new HLIOScope(body);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_DECISION, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					ArrayList<HLCodeBuilder> alternatives = hlCodeBuildersFromAST(ast.getAsDefaultContainer(Symbol.Map.KEY_ALTERNATIVES));
					return new HLDecision(alternatives);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_LOOKUP_CHAIN, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					HLCodeBuilder body = fromAST(ast.get(Symbol.Map.KEY_BODY));
					ArrayList<String> slots = identifierStringsFromAST(ast.getAsDefaultContainer(Symbol.Map.KEY_SLOTS));
					return new HLLookupChain(body, slots);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_IO_SCOPE_USAGE, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					HLCodeBuilder body = fromAST(ast.get(Symbol.Map.KEY_BODY));
					int scope = ast.getAsInt(Symbol.Map.KEY_SCOPE);
					return new HLIOScopeUsage(body, scope);
				}
			});

//			// TODO: This should not occur!!!
//			// TODO: Separate into variable usage and module usage; this means that two corresponding new HLCodeBuilder classes should be introduced
//			factoryMap.put(Symbol.Map.AST_TYPE_IDENTIFIER_USAGE, new Func1<HLCodeBuilder, Symbol.Map>() {
//				@Override
//				public HLCodeBuilder call(Map ast) {
//					Symbol.Map resourceLocationSymbol = (Symbol.Map)ast.get(Symbol.Map.KEY_RESOURCE_LOCATION);
//					ResourceLocation resourceLocation = new ResourceLocation(
//						resourceLocationSymbol.getAsString(Symbol.Map.KEY_RESOURCE_NAME), 
//						resourceLocationSymbol.getAsInt(Symbol.Map.KEY_LINE), 
//						resourceLocationSymbol.getAsInt(Symbol.Map.KEY_COLUMN));
//					String identifier = ast.getAsString(Symbol.Map.KEY_IDENTIFIER);
//					return new HLIdentifier(identifier, resourceLocation);
//				}
//			});
			

			
			factoryMap.put(Symbol.Map.AST_TYPE_MODULE_USAGE, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					// The identifier of the module must be extracted
					// If expanding into same call scope, then error; static error?
					Symbol moduleAst = ast.get(Symbol.Map.KEY_VALUE);
//					return new HLModuleUsage(moduleAst);
					return fromAST(moduleAst);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_VARIABLE_USAGE, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					String identifier = ast.getAsString(Symbol.Map.KEY_IDENTIFIER);
					return new HLVariableUsage(identifier);
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_CALL, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					Symbol target = ast.get(Symbol.Map.KEY_TARGET);
					HLCodeBuilder hlCodeBuilder = fromAST(target);
					CodeBuilder codeBuilder = new CodeBuilder();
					
					Object falseJumpLabel = codeBuilder.createLabel();
					codeBuilder.setFalseJump(falseJumpLabel);
					
					hlCodeBuilder.appendTo(codeBuilder);
					
					codeBuilder
						.acceptFrame()
						.label(falseJumpLabel)
						.rejectFrame();
					
					CodeBuilder.Build build = codeBuilder.build();
					return new HLCall(build);
				}
			});
			
			

			factoryMap.put(Symbol.Map.AST_TYPE_LIST, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					Symbol initializationSymbol = ast.get(Symbol.Map.KEY_INITIALIZATION);
					if(initializationSymbol != null) {
						HLCodeBuilder initialization = fromAST(initializationSymbol);
						return new HLList(initialization);
					} else {
						return new HLList();
					}
				}
			});

			factoryMap.put(Symbol.Map.AST_TYPE_MAP, new Func1<HLCodeBuilder, Symbol.Map>() {
				@Override
				public HLCodeBuilder call(Map ast) {
					Container.Default slotSets = (Container.Default)ast.get(Symbol.Map.KEY_SLOT_SETS);
					
					HLMap map = new HLMap();
					
					for(Symbol item: slotSets.list) {
						Symbol.Map mapItem = (Symbol.Map)item;
						String slot = mapItem.getAsString(Symbol.Map.KEY_SLOT);
						HLCodeBuilder value = fromAST((Symbol.Map)mapItem.get(Symbol.Map.KEY_VALUE));
						
						map.setSlot(slot, value);
					}
					
					return map;
				}
			});
			
			return factoryMap;
		}
		
		protected static ArrayList<HLCodeBuilder> hlCodeBuildersFromAST(Container.Default items) {
			ArrayList<HLCodeBuilder> itemsBuilder = new ArrayList<HLCodeBuilder>();
			
			for(Symbol item: items.list) {
				HLCodeBuilder itemCodeBuilder = fromAST(item);
				itemsBuilder.add(itemCodeBuilder);
			}
			
			return itemsBuilder;
		}
		
		protected static ArrayList<String> identifierStringsFromAST(Container.Default items) {
			ArrayList<String> itemsBuilder = new ArrayList<String>();
			
			for(Symbol item: items.list) {
				String string = ((Symbol.Identifier)item).name;
				itemsBuilder.add(string);
			}
			
			return itemsBuilder;
		}

		public static HLCodeBuilder fromAST(Symbol ast) {
			if(ast instanceof Symbol.Map) {
				Symbol.Map mapAST = (Symbol.Map)ast;
				return fromAST(mapAST);
			}
			
			return null;
		}
		
		private static HLCodeBuilder fromAST(Symbol.Map ast) {
			Symbol.Identifier astType = ast.getAsIdentifier(Symbol.Map.KEY_TYPE);
			
			Func1<HLCodeBuilder, Symbol.Map> astFactory = astTypeToFactoryMap.get(astType);
			
			if(astFactory == null)
				new String();
			
			return astFactory.call(ast);
		}
	}
	
	void setup(HLCodeBuilder.Validation validation);
	void validate(HLCodeBuilder.Validation validation);
	void appendTo(CodeBuilder codeBuilder);
}
