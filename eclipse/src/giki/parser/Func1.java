package giki.parser;

public interface Func1<R, A0> {
	R call(A0 arg0);
	
	public static class Constant<R, A0> implements Func1<R, A0> {
		private final R result;
		
		private Constant(R result) {
			this.result = result;
		}
		
		public static <R, A0> Constant<R, A0> wrap(R result) {
			return new Constant<R, A0>(result);
		}
		
		@Override
		public R call(A0 arg0) {
			return result;
		}
	}
}
