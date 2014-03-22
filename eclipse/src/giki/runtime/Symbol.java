package giki.runtime;

import giki.runtime.Container.Default;
import giki.runtime.Symbol.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Symbol {
	private static Hashtable<String, Symbol.Identifier> nameToIdentifierMap = new Hashtable<String, Symbol.Identifier>();
	
	public static Identifier getIdentifier(String name) {
		Symbol.Identifier identifier = nameToIdentifierMap.get(name);
		if(identifier == null) {
			identifier = new Symbol.Identifier(name);
			nameToIdentifierMap.put(name, identifier);
		}
		return identifier;
	}
	
	public static Symbol.Integer getInteger(int value) {
		return new Symbol.Integer(value);
	}
	
	public static Symbol.Primitive getPrimitive(int value) {
		return new Symbol.Primitive(value);
	}

	public static class Identifier extends Symbol {
		public final String name;
		public final int symbolCode;
		
		public Identifier(String name) {
			this.name = name;
			this.symbolCode = SymbolTable.getSymbolCode(name);
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof Identifier && this.symbolCode == ((Identifier)obj).symbolCode;
		}
		
		@Override
		public String toString() {
			return name.replace("\t", "\\t").replace("\n", "\\n").replace("\r", "\\r");
		}
	}
	
	public static class Integer extends Symbol {
		public final int value;
		
		public Integer(int value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof Integer && this.value == ((Integer)obj).value;
		}
		
		@Override
		public String toString() {
			return "" + value;
		};
	}
	
	public static class Primitive extends Symbol {
		private int value;
		
		public Primitive(int value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof Identifier && this.value == ((Primitive)obj).value;
		}
		
		@Override
		public String toString() {
			return "<" + value + ">";
		};
	}
	
	public static class Map extends Symbol {
		public static final Identifier AST_TYPE_ASSIGNMENT = Symbol.Identifier.getIdentifier("assignment");
		public static final Identifier AST_TYPE_VARIABLE_DECLARATION = Symbol.Identifier.getIdentifier("varDec");
		public static final Identifier AST_TYPE_INSTRUCTION = Symbol.Identifier.getIdentifier("instr");
		public static final Identifier AST_TYPE_NOT = Symbol.Identifier.getIdentifier("not");
		public static final Identifier AST_TYPE_LOOP = Symbol.Identifier.getIdentifier("loop");
		public static final Identifier AST_TYPE_GROUP = Symbol.Identifier.getIdentifier("group");
		public static final Identifier AST_TYPE_PIPELINE = Symbol.Identifier.getIdentifier("pipeline");
		public static final Identifier AST_TYPE_TO = Symbol.Identifier.getIdentifier("to");
		public static final Identifier AST_TYPE_FROM = Symbol.Identifier.getIdentifier("from");
		public static final Identifier AST_TYPE_IO_SCOPE = Symbol.Identifier.getIdentifier("ioScope");
		public static final Identifier AST_TYPE_DECISION = Symbol.Identifier.getIdentifier("dec");
		public static final Identifier AST_TYPE_LOOKUP_CHAIN = Symbol.Identifier.getIdentifier("lookupChain");
		public static final Identifier AST_TYPE_IO_SCOPE_USAGE = Symbol.Identifier.getIdentifier("ioScopeUse");
		public static final Identifier AST_TYPE_IDENTIFIER_USAGE = Symbol.Identifier.getIdentifier("idUse");
		public static final Identifier AST_TYPE_LIST = Symbol.Identifier.getIdentifier("list");
		public static final Identifier AST_TYPE_MAP = Symbol.Identifier.getIdentifier("map");
		public static final Identifier AST_TYPE_MODULE = Symbol.Identifier.getIdentifier("module");
		public static final Identifier AST_TYPE_PLACE_HOLDER = Symbol.Identifier.getIdentifier("placeHolder");
		public static final Identifier AST_TYPE_MODULE_USAGE = Symbol.Identifier.getIdentifier("moduleUsage");
		public static final Identifier AST_TYPE_VARIABLE_USAGE = Symbol.Identifier.getIdentifier("variableUsage");
		public static final Identifier AST_TYPE_CALL = Symbol.Identifier.getIdentifier("call");
		
		public static final java.lang.Integer KEY_IDENTIFIER = SymbolTable.getSymbolCodeInteger("identifier");
		public static final java.lang.Integer KEY_VALUE = SymbolTable.getSymbolCodeInteger("value");
		public static final java.lang.Integer KEY_INITIALIZATION = SymbolTable.getSymbolCodeInteger("initialization");
		public static final java.lang.Integer KEY_OPCODE = SymbolTable.getSymbolCodeInteger("opcode");
		public static final java.lang.Integer KEY_OPERAND = SymbolTable.getSymbolCodeInteger("operand");
		public static final java.lang.Integer KEY_BODY = SymbolTable.getSymbolCodeInteger("body");
		public static final java.lang.Integer KEY_TYPE = SymbolTable.getSymbolCodeInteger("type");
		public static final java.lang.Integer KEY_ITEMS = SymbolTable.getSymbolCodeInteger("items");
		public static final java.lang.Integer KEY_TARGET = SymbolTable.getSymbolCodeInteger("target");
		public static final java.lang.Integer KEY_SOURCE = SymbolTable.getSymbolCodeInteger("source");
		public static final java.lang.Integer KEY_ALTERNATIVES = SymbolTable.getSymbolCodeInteger("alternatives");
		public static final java.lang.Integer KEY_SLOTS = SymbolTable.getSymbolCodeInteger("slots");
		public static final java.lang.Integer KEY_SCOPE = SymbolTable.getSymbolCodeInteger("scope");
		public static final java.lang.Integer KEY_RESOURCE_LOCATION = SymbolTable.getSymbolCodeInteger("resourceLocation");
		public static final java.lang.Integer KEY_RESOURCE_NAME = SymbolTable.getSymbolCodeInteger("resourceName");
		public static final java.lang.Integer KEY_LINE = SymbolTable.getSymbolCodeInteger("line");
		public static final java.lang.Integer KEY_COLUMN = SymbolTable.getSymbolCodeInteger("column");
		public static final java.lang.Integer KEY_SLOT_SETS = SymbolTable.getSymbolCodeInteger("slotSets");
		public static final java.lang.Integer KEY_PIPES = SymbolTable.getSymbolCodeInteger("pipes");
		public static final java.lang.Integer KEY_SLOT = SymbolTable.getSymbolCodeInteger("slot");
		public static final java.lang.Integer KEY_MODIFIERS = SymbolTable.getSymbolCodeInteger("modifiers");
		public static final java.lang.Integer KEY_INNER_MODULES = SymbolTable.getSymbolCodeInteger("innerModules");
		
		private java.util.Map<java.lang.Integer, Symbol> map;
		
		public Map() {
			this(new Hashtable<java.lang.Integer, Symbol>());
		}
		
		public Map(java.util.Map<java.lang.Integer, Symbol> map) {
			this.map = map;
		}
		
		public void put(java.lang.Integer key, Symbol value) {
			map.put(key, value);
		}

		public Map with(java.lang.Integer key, Symbol value) {
			put(key, value);
			return this;
		}

		public Symbol get(java.lang.Integer key) {
			return map.get(key);
		}
		
		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			
			str.append("{");
			
			for(java.util.Map.Entry<java.lang.Integer, Symbol> entry: map.entrySet()) {
				if(str.length() > 1)
					str.append(", ");
				String identifier = SymbolTable.getSymbolString(entry.getKey());
				String value = entry.getValue().toString();
				str.append(identifier + ": " + value);
			}
			
			str.append("}");
			
			return str.toString();
		}

		public Map composeWith(Map other) {
			Hashtable<java.lang.Integer, Symbol> newMap = new Hashtable<java.lang.Integer, Symbol>(this.map);
			
			newMap.putAll(other.map);

			return new Map(newMap);
		}
		
		public String getAsString(java.lang.Integer key) {
			return getAsIdentifier(key).name;
		}
		
		public int getAsInt(java.lang.Integer key) {
			return ((Symbol.Integer)get(key)).value;
		}

		public Identifier getAsIdentifier(java.lang.Integer key) {
			return (Symbol.Identifier)get(key);
		}

		public Container.Default getAsDefaultContainer(java.lang.Integer key) {
			return (Container.Default)get(key);
		}
	}

	public static Symbol.Map astInstruction(int opcode) {
		return ast(Symbol.Map.AST_TYPE_INSTRUCTION)
			.with(Symbol.Map.KEY_OPCODE, Integer.getInteger(opcode));
	}

	public static Symbol.Map astInstruction(int opcode, Symbol operand) {
		// TODO: Consider using Identifiers for opcode - and then looking of the corresponding int opcode during actual code generation
		// Why? Because, then low-level code generation is not dependent on int-based opcodes. This, opcodes may changed behind the scenes
		// - and it is likely more readable.
		return ast(Symbol.Map.AST_TYPE_INSTRUCTION)
			.with(Symbol.Map.KEY_OPCODE, Integer.getInteger(opcode))
			.with(Symbol.Map.KEY_OPERAND, operand);
	}

	public static Symbol astNot(Symbol body) {
		return ast(Symbol.Map.AST_TYPE_NOT)
			.with(Symbol.Map.KEY_BODY, body);
	}

	public static Symbol astLoop(Symbol body) {
		return ast(Symbol.Map.AST_TYPE_LOOP)
			.with(Symbol.Map.KEY_BODY, body);
	}

	public static Symbol astSequence(Symbol... items) {
		return astGroup(Arrays.asList(items));
	}

	public static Symbol astGroup(List<Symbol> items) {
		return ast(Symbol.Map.AST_TYPE_GROUP)
			.with(Symbol.Map.KEY_ITEMS, new Container.Default(items));
	}

	public static Symbol.Map ast(String type) {
		return ast(Symbol.getIdentifier(type));
	}

	public static Symbol.Map ast(Symbol.Identifier type) {
		return new Symbol.Map()
			.with(Symbol.Map.KEY_TYPE, type);
	}

	public static Symbol astPipeline(Symbol... pipes) {
		return astPipeline(Arrays.asList(pipes));
	}

	public static Symbol astPipeline(List<Symbol> pipes) {
		return ast(Symbol.Map.AST_TYPE_PIPELINE)
			.with(Symbol.Map.KEY_PIPES, new Container.Default(pipes));
	}

	public static Symbol astTo(Symbol target, Symbol body) {
		return ast(Symbol.Map.AST_TYPE_TO)
			.with(Symbol.Map.KEY_TARGET, target)
			.with(Symbol.Map.KEY_BODY, body);
	}

	public static Symbol astFrom(Symbol source, Symbol body) {
		return ast(Symbol.Map.AST_TYPE_FROM)
			.with(Symbol.Map.KEY_SOURCE, source)
			.with(Symbol.Map.KEY_BODY, body);
	}

	public static Symbol astIOScope(Symbol body) {
		return ast(Symbol.Map.AST_TYPE_IO_SCOPE)
			.with(SymbolTable.getSymbolCode("body"), body);
	}

	public static Symbol astDecision(List<Symbol> alternatives) {
		return ast(Symbol.Map.AST_TYPE_DECISION)
			.with(Symbol.Map.KEY_ALTERNATIVES, new Container.Default(alternatives));
	}

	public static Symbol astAssignment(String identifier, Symbol value) {
		return ast(Symbol.Map.AST_TYPE_ASSIGNMENT)
			.with(Symbol.Map.KEY_IDENTIFIER, Symbol.getIdentifier(identifier))
			.with(Symbol.Map.KEY_VALUE, value);
	}

	public static Symbol astVariableDeclaration(String identifier,
			Symbol initialization) {
		Symbol.Map astVariableDeclaration = ast(Symbol.Map.AST_TYPE_VARIABLE_DECLARATION)
			.with(Symbol.Map.KEY_IDENTIFIER, Symbol.getIdentifier(identifier));
		if(initialization != null)
			astVariableDeclaration.put(Symbol.Map.KEY_INITIALIZATION, initialization);
		return astVariableDeclaration;
	}

	public static Symbol astReduce(ArrayList<Symbol> items, Symbol reduction) {
		Symbol last = items.get(items.size() - 1);
		Symbol lastButOne = items.get(items.size() - 2);
		
		ArrayList<Symbol> pipes = new ArrayList<Symbol>();
		
		pipes.add(astSequence(last, lastButOne));
		
		for(int i = items.size() - 3; i >= 0; i--) {
			Symbol part = items.get(i);
			pipes.add(astSequence(reduction, part));
		}
		
		pipes.add(reduction);
		
		return astPipeline(pipes);
	}

	public static Symbol astInterval(Symbol lhs, Symbol rhs) {
		return astSequence(
			astInstruction(Instruction.TYPE_PEEK_GTE, lhs),
			astInstruction(Instruction.TYPE_PEEK_LTE, rhs));
	}

	public static Symbol astLookupChain(Symbol body, ArrayList<Symbol> slotIdentifiers) {
		return ast(Symbol.Map.AST_TYPE_LOOKUP_CHAIN)
			.with(Symbol.Map.KEY_BODY, body)
			.with(Symbol.Map.KEY_SLOTS, new Container.Default(slotIdentifiers));
	}

	public static Symbol astIOScopeUsage(int ioScope, Symbol body) {
		return ast(Symbol.Map.AST_TYPE_IO_SCOPE_USAGE)
			.with(Symbol.Map.KEY_SCOPE, Symbol.getInteger(ioScope))
			.with(Symbol.Map.KEY_BODY, body);
	}

	public static Symbol astIdentifierUsage(Symbol resourceLocation, String identifier) {
		return ast(Symbol.Map.AST_TYPE_IDENTIFIER_USAGE)
			.with(Symbol.Map.KEY_RESOURCE_LOCATION, resourceLocation)
			.with(Symbol.Map.KEY_IDENTIFIER, Symbol.getIdentifier(identifier));
	}

	public static Symbol resourceLocation(String resourceName, int line, int column) {
		return new Symbol.Map()
			.with(Symbol.Map.KEY_RESOURCE_NAME, Symbol.getIdentifier(resourceName))
			.with(Symbol.Map.KEY_LINE, Symbol.getInteger(line))
			.with(Symbol.Map.KEY_COLUMN, Symbol.getInteger(column));
	}

	public static Symbol astList(Symbol initialization) {
		return ast(Symbol.Map.AST_TYPE_LIST)
			.with(Symbol.Map.KEY_INITIALIZATION, initialization);
	}

	public static Symbol astList() {
		return ast(Symbol.Map.AST_TYPE_LIST);
	}

	public static Symbol astMap(List<Symbol> slotSets) {
		return ast(Symbol.Map.AST_TYPE_MAP)
			.with(Symbol.Map.KEY_SLOT_SETS, new Container.Default(slotSets));
	}

	public static Symbol astQuote(String value) {
		return astQuote(Symbol.getIdentifier(value));
	}

	public static Symbol astQuote(Symbol value) {
		return astInstruction(Instruction.TYPE_QUOTE, value);
	}

	public static Symbol astPlaceHolder(String identifier) {
		return ast(Symbol.Map.AST_TYPE_PLACE_HOLDER)
			.with(Symbol.Map.KEY_IDENTIFIER, Symbol.getIdentifier(identifier));
	}

	public static Symbol astCall(Symbol ast) {
		return ast(Symbol.Map.AST_TYPE_CALL)
			.with(Symbol.Map.KEY_TARGET, ast);
	}
}
