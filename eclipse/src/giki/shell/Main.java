package giki.shell;

import giki.diagnostics.StopWatch;
import giki.parser.FileResourceStore;
import giki.parser.ResourceStore;
import giki.runtime.Container;
import giki.runtime.Input;
import giki.runtime.Output;
import giki.runtime.Symbol;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
////		HLCodeBuilder.HLSequenceBuilder hlCodeBuilder = new HLCodeBuilder.HLSequenceBuilder();
////		
////		hlCodeBuilder.append(new HLCodeBuilder.HLPipeline()
////			.append(new HLCodeBuilder.HLSequenceBuilder().quote(Symbol.getIdentifier("z")))
////			.append(new HLCodeBuilder.HLSequenceBuilder().move().quote(Symbol.getIdentifier("y")))
////			.append(new HLCodeBuilder.HLSequenceBuilder().move().move().quote(Symbol.getIdentifier("x"))));
////		hlCodeBuilder.acceptFrame();
//		
////		for(int i = 0; i < 1000; i++) {
////			Machine machineDummy = new Machine(new IOBuffer(), new IOBuffer(), new Instruction[]{Instruction.get(Instruction.TYPE_ACCEPT_FRAME)}, new Symbol[0]);
////			machineDummy.evaluate();
////		}
//		
//		HLSequence hlCodeBuilder = new HLSequence();
//		
////		hlCodeBuilder
////			.append(new HLDecision()
////				.alternative(new HLSequence().eq("a").quote("IsA"))
////				.alternative(new HLSequence().eq("b").quote("IsB"))
////				.alternative(new HLSequence().eq("c").quote("IsC"))
////			)
////			.list(new HLSequence().quote("a").quote("b").quote("c"))
////		;
//		
////		hlCodeBuilder
////			.loop(new HLSequence()
////				.not(new HLSequence()
////					.eof()
////				)
////				.list(new HLDecision()
////					.alternative(new HLSequence().eq("a").quote("IsA"))
////					.alternative(new HLSequence().eq("b").quote("IsB"))
////					.alternative(new HLSequence().eq("c").quote("IsC"))
////				)
////			)
////		;
//		
////		hlCodeBuilder
////			.loop(new HLSequence()
////				.not(new HLSequence()
////					.eof()
////				)
////				.move()
////			)
////		;
//		
////		hlCodeBuilder
////			.not(new HLSequence()
////				.eof()
////			)
////		;
//		
////		hlCodeBuilder
////			.list(new HLSequence().quote("a").quote("b").quote("c"))
////			.list(new HLSequence().quote("i").quote("j").quote("k"))
////		;
//		
////		hlCodeBuilder
////			.list(
////				new HLSequence()
////					.list(new HLSequence().quote("a").quote("b").quote("c"))
////					.list(new HLSequence().quote("i").quote("j").quote("k"))
////			)
////		;
//		
////		hlCodeBuilder
////			.setOutputVariable("a")
////			.move()
////			.setOutputVariable("b")
////			.move()
////			.setOutputVariable("c")
////			.move()
////			.setOutputTarget()
////			.lookupVariable("a")
////			.lookupVariable("b")
////			.lookupVariable("c")
////		;
//		
////		hlCodeBuilder.append(new HLPipeline()
////			.append(new HLSequence().quote(")").quote(")"))
////			.append(new HLSequence().eq(")").eq(")"))
////		);
////		hlCodeBuilder.loop(new HLSequence().move());
//		
////		hlCodeBuilder.quote("x");
////		hlCodeBuilder.quote("x");
//		
//		hlCodeBuilder.acceptFrame();
//		
//		CodeBuilder codeBuilder = new CodeBuilder();
//		
//		Object falseJumpLabel = codeBuilder.createLabel();
//		codeBuilder.setFalseJump(falseJumpLabel);
//		
//		hlCodeBuilder.appendTo(codeBuilder);
////		Object loop = codeBuilder.createLabel();
////		Object acceptLabel = codeBuilder.createLabel();
////		Object rejectLabel = codeBuilder.createLabel();
////		codeBuilder
////				.quote(Symbol.getIdentifier("START"))
////			.label(loop)
////				.setFalseJump(acceptLabel)
////				.move()
//////				.setFalseJump(rejectLabel)
//////				.move()
//////				.move()
////				.jump(loop)
////			.label(acceptLabel)
////				.quote(Symbol.getIdentifier("END"))
////				.acceptFrame()
////			.label(rejectLabel)
////				.rejectFrame()
////		;
//		
//		codeBuilder
//			.acceptFrame()
//			.label(falseJumpLabel)
//			.rejectFrame();
//		
//		
//		CodeBuilder.Build build = codeBuilder.build();
//		
////		Instruction[] code = new Instruction[] {
////			Instruction.get(Instruction.TYPE_SET_FALSE_JUMP, 5),
////			Instruction.get(Instruction.TYPE_MOVE),
////			Instruction.get(Instruction.TYPE_SET_FALSE_JUMP, 6),
////			Instruction.get(Instruction.TYPE_MOVE),
////			Instruction.get(Instruction.TYPE_JUMP, 0),
////			Instruction.get(Instruction.TYPE_ACCEPT_FRAME),
////			Instruction.get(Instruction.TYPE_REJECT_FRAME)
////		};
//		
//		Input input = new IOBuffer("abcababbbac");
////		Input input = new IOBuffer(Arrays.asList(Symbol.getIdentifier("a"), Symbol.getIdentifier("b"), Symbol.getIdentifier("c")));
////		Input input = new IOBuffer(Arrays.asList(Symbol.getIdentifier("b")));
////		Input input = new IOBuffer(Arrays.asList(Symbol.getIdentifier("c")));
////		Input input = new IOBuffer(Arrays.asList(Symbol.getIdentifier("d")));
////		Input input = new IOBuffer();
//		Output output = new IOBuffer();
//		Symbol[] variables = new Symbol[build.variableCount];
//		Machine machine = new Machine(input, output, build.code, variables, build.interCount);
//
//		System.out.println("pre:");
//		System.out.println("in=" + input);
//		System.out.println("out=" + output);
//		System.out.println();
//		
//		StopWatch sw = new StopWatch();
//		sw.start();
//		
//		machine.evaluate();
//		
//		sw.stop();
//		sw.printElapsed(System.out, "Shell evaluation");
//
//		System.out.println("post:");
//		System.out.println("in=" + input);
//		System.out.println("out=" + output);
//		
//		if(machine.getResult() == Machine.RESULT_ACCEPTED)
//			System.out.print("Accepted");
//		else
//			System.out.print("Rejected");
//		
//		if(1 != 2)
//			return;
		
		
		
		if(args.length < 1) {
			System.out.println("Please supply a single argument representing the path of the file to evaluate.");
			return;
		}
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		final String filePath = args[0];
		final String[] gikiArgs = new String[args.length - 1];
		System.arraycopy(args, 1, gikiArgs, 0, gikiArgs.length);
//		giki.server.Response_OLD response = new Response_OLD(System.out);
		
		File file = new File(filePath);
		String directory = file.getParentFile().getCanonicalPath() + File.separator;
		String name = file.getName().substring(0, file.getName().lastIndexOf('.'));
		
		ResourceStore resourceStore = new FileResourceStore(directory);
		
		final ArrayList<Symbol> gikiArgsBuilder = new ArrayList<Symbol>();
		for(int i = 0; i < args.length; i++) {
			gikiArgsBuilder.add(Symbol.getIdentifier(args[i]));
		}

		Input input = new Container.Default(gikiArgsBuilder).createInput();
		Output output = new Container.Default().createOutput();
		
		giki.server.Response response = new giki.server.Response(resourceStore, System.out);
		response.parse(name, input, output);
		
		stopWatch.stop();
		stopWatch.printElapsed(System.out, "Shell evaluation");
	}
}
