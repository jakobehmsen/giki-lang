package giki;

import java.util.Stack;

public class Process {
	private static class Frame {
		public int index;
		public final Idag receiver;
		public final List code;
		
		public Frame(Idag receiver, List code) {
			this.receiver = receiver;
			this.code = code;
		}
	}

	private Stack<Idag> stack = new Stack<Idag>();
	private Stack<Frame> frames = new Stack<Frame>();
	
	public Process(Idag root, Idag code) {
		frames.push(new Frame(root, (List)code));
	}
	
	public boolean atEnd() {
		return frames.size() == 1 && frames.get(0).index == frames.get(0).code.items.length;
	}
	
	public int process(int maxSteps) {
		int stepsLeft = maxSteps;
		Frame currentFrame = frames.get(frames.size() - 1);
		while(stepsLeft >= 0) {
			if(currentFrame.index < currentFrame.code.items.length) {
				Idag instruction = currentFrame.code.items[currentFrame.index];
				
				if(instruction instanceof Int) {
					Int primitive = (Int)instruction;
					
					switch(primitive.value) { 
					case Int.OPCODE_POP: {
						stack.pop();
						currentFrame.index++;
						break;
					} case Int.OPCODE_PUSH: {
						currentFrame.index++;
						Idag object = currentFrame.code.items[currentFrame.index];
						stack.push(object);
						currentFrame.index++;
						break;
					} case Int.OPCODE_PUSH_RECEIVER: {
						stack.push(currentFrame.receiver);
						currentFrame.index++;
						break;
					} case Int.OPCODE_PUSH_HOST: {
						stack.push(Host.INSTANCE);
						currentFrame.index++;
						break;
					} case Int.OPCODE_RETURN: {
						currentFrame.index++;
						frames.pop();
						currentFrame = frames.peek();
						break;
					} case Int.OPCODE_ENQUIRE: {
						currentFrame.index++;
						Idag response = stack.pop();
						Idag receiver = stack.pop();
						currentFrame = new Frame(receiver, (List)response);
						frames.push(currentFrame);
						break;
					} case Int.OPCODE_NEW: {
						Idag agent = stack.pop();
						
						Idag object = Host.INSTANCE.newObject(agent);
						stack.push(object);
						
						currentFrame.index++;
						break;
					} case Int.OPCODE_NEW_LIST: {
						currentFrame.index++;
						Int length = (Int)currentFrame.code.items[currentFrame.index];
						currentFrame.index++;
						Idag[] items = new Idag[length.value];
						System.arraycopy(currentFrame.code.items, currentFrame.index, items, 0, length.value);
						currentFrame.index += length.value;
						stack.push(new List(items));
						break;
					} 
					
					case Int.OPCODE_AGENT_SEND:  {
						Int term = (Int)stack.pop();
						Idag receiver = stack.pop();

						currentFrame.index++;
						
						if(receiver.agent instanceof Host) {
							Idag response = receiver.lookup(term);
							currentFrame = new Frame(receiver, (List)response);
							frames.push(currentFrame);
						} else {
							// TODO: Send message to agent
						}
						
						break;
					} case Int.OPCODE_AGENT_DEFINE: {
						Idag meaning = stack.pop();
						Int term = (Int)stack.pop();
						Idag object = stack.pop();
						
						if(object.agent instanceof Host) {
							object.define(term, meaning);
						} else {
							// TODO: Send message to agent
						}
						
						currentFrame.index++;
						break;
					} case Int.OPCODE_AGENT_LOOKUP: {
						Int term = (Int)stack.pop();
						Idag object = stack.pop();
						
						if(object.agent instanceof Host) {
							Idag meaning = object.lookup(term);
							stack.push(meaning);
						} else {
							// TODO: Send message to agent
						}
						
						currentFrame.index++;
						break;
					}
					
					case Int.OPCODE_INTS_ADD: {
						Int rhs = (Int)stack.pop();
						Int lhs = (Int)stack.pop();
						
						stack.push(Int.get(lhs.value + rhs.value));
						
						currentFrame.index++;
						break;
					} case Int.OPCODE_INTS_SUB: {
						Int rhs = (Int)stack.pop();
						Int lhs = (Int)stack.pop();
						
						stack.push(Int.get(lhs.value - rhs.value));
						
						currentFrame.index++;
						break;
					} case Int.OPCODE_INTS_MUL: {
						Int rhs = (Int)stack.pop();
						Int lhs = (Int)stack.pop();
						
						stack.push(Int.get(lhs.value * rhs.value));
						
						currentFrame.index++;
						break;
					} case Int.OPCODE_INTS_DIV: {
						Int rhs = (Int)stack.pop();
						Int lhs = (Int)stack.pop();
						
						stack.push(Int.get(lhs.value / rhs.value));
						
						currentFrame.index++;
						break;
					}
					}
				} else {
					currentFrame.index = currentFrame.code.items.length;
					break;
				}
				
				stepsLeft--;
			} else
				break; // Or pop stack frame
		}
		
		return maxSteps - stepsLeft;
	}
	
	@Override
	public String toString() {
//		return frames.toString();
		return stack.toString();
	}
}
