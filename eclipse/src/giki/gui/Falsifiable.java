package giki.gui;

import giki.parser.ParseMessage;

import java.util.List;

public class Falsifiable<A> {
	public final List<ParseMessage> messages;
	public final A acceptedEntity;
	
	private Falsifiable(A acceptedEntity) {
		this.messages = null;
		this.acceptedEntity = acceptedEntity;
	}
	
	private Falsifiable(List<ParseMessage> messages) {
		this.messages = messages;
		this.acceptedEntity = null;
	}
	
	public boolean accepted() {
		return acceptedEntity != null;
	}
	
	public boolean rejected() {
		return acceptedEntity == null;
	}
	
	public void appendMessages(List<ParseMessage> messages) {
		if(this.messages != null)
			messages.addAll(this.messages);
	}
	
	public static <E> Falsifiable<E> accept(E acceptedEntity) {
		return new Falsifiable<E>(acceptedEntity);
	}
	
	public static <E> Falsifiable<E> reject(List<ParseMessage> messages) {
		return new Falsifiable<E>(messages);
	}
	
	public static <E> Falsifiable<E> acceptIf(E acceptedEntity, Falsifiable<?> dependency) {
		return acceptIf(acceptedEntity, dependency.messages);
	}
	
	public static <E> Falsifiable<E> acceptIf(E acceptedEntity, List<ParseMessage> messages) {
		if(messages != null && messages.size() > 0)
			return new Falsifiable<E>(messages);
		return new Falsifiable<E>(acceptedEntity);
	}
}
