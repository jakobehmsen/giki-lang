package giki.parser;

public interface Func0<R> {
	R call();
	
	public static class Constant<R> implements Func0<R> {
		private final R result;
		
		private Constant(R result) {
			this.result = result;
		}
		
		public static <R> Constant<R> wrap(R result) {
			return new Constant<R>(result);
		}
		
		@Override
		public R call() {
			return result;
		}
	}
}
