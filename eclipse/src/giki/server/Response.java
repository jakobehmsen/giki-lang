package giki.server;

import giki.parser.Func0;
import giki.parser.ModuleApplication;
import giki.parser.ResourceStore;
import giki.parser.ParseMessage;
import giki.runtime.CodeBuilder;
import giki.runtime.Input;
import giki.runtime.Machine;
import giki.runtime.Output;
import giki.runtime.Symbol;

import java.io.IOException;
import java.io.PrintStream;

public class Response {
	private ResourceStore resourceStore;
	private PrintStream out;
	
	public Response(ResourceStore resourceStore, PrintStream out) {
		this.resourceStore = resourceStore;
		this.out = out;
	}
	
//	public void parse(final String name, String[] args) {
//		final ArrayList<Symbol> gikiArgs = new ArrayList<Symbol>();
//		for(int i = 0; i < args.length; i++) {
//			gikiArgs.add(Symbol.getIdentifier(args[i]));
//		} 
	
	public void parse(final String name, final Input input, final Output output) throws IOException {
		final ModuleApplication moduleApplication = new ModuleApplication(resourceStore, name);
		
		final CodeBuilder.Build moduleBuild = moduleApplication.getEntryPointModuleBuild();
		
//		final giki.parser.ParseResult parseResult = printTime("Parse", new Func<giki.parser.ParseResult>() {
//			@Override
//			public giki.parser.ParseResult run() {
//				try {
//					giki.parser.Parser parser = new giki.parser.Parser(resourceStore, out);
//					return parser.parse(name);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//			
//		});
		
		if(moduleApplication.getParseMessages().size() > 0) {
			out.println("Issues: ");
			for(ParseMessage message: moduleApplication.getParseMessages()) {
//				String fileName = new File(unresolvableDependency.module.ressourcePath).getName();
//				out.println(fileName + "(" + unresolvableDependency.dependency.getFileLocation().line + "," + unresolvableDependency.dependency.getFileLocation().column + "): Cannot resolve " + unresolvableDependency.dependency.getIdentifier() + ".");
				out.println(message.location.resourceName + "(" + message.location.line + "," + message.location.column + "): " + message.text);
			}
		} else {
			printTime("Evaluate all", new Runnable() {
				@Override
				public void run() {
//					Input input = new Container.Default(gikiArgs).createInput();
//					Output output = new Container.Default().createOutput();
					
					out.println("IN=" + input);
					out.println("OUT=" + output);

//					CodeBuilder.Build moduleBuild = moduleApplication.getModuleBuild(name);
					Symbol[] variables = new Symbol[moduleBuild.variableCount];
					final Machine machine = new Machine(input, output, moduleBuild.code, variables, moduleBuild.interCount);
					printTime("Evaluate", new Runnable() {
						@Override
						public void run() {
							while(machine.getResult() == Machine.RESULT_ABSENT)
								machine.evaluate();
						}
					});
					
					out.println(machine.getResult() == Machine.RESULT_ACCEPTED ? "Accepted" : "Rejected");
					out.println("IN=" + input);
					out.println("OUT=" + output);
				}
			});
		}
	}
	
	private void printTime(String name, final Runnable runnable) {
		printTime(name, new Func0<Object>() {
			@Override
			public Object call() {
				runnable.run();
				return null;
			}
		});
	}
	
	private <R> R printTime(String name, Func0<R> func) {
		long start = System.nanoTime();
		R result = func.call();
		long end = System.nanoTime();
		long time =  end - start;
		
		out.println(name + ": " + time / 1000000.0 + " millis.");
		
		return result;
	}
}
