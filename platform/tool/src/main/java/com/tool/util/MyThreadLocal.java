package com.tool.util;

public class MyThreadLocal {

	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
		@Override
		protected Object initialValue() {
			System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
			return null;
		}
	};
	public static void main(String[] args) {
		new Thread(new MyIntegerTask("IntegerTask1")).start();
		new Thread(new MyIntegerTask("IntegerTask2")).start();
	}

	public static class MyIntegerTask implements Runnable {

		private String name;

		public MyIntegerTask(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				// ThreadLocal.get方法获取线程变量
				if (MyThreadLocal.threadLocal.get() == null) {
					MyThreadLocal.threadLocal.set(0);
					System.out.println("线程" + name + ": 0");
				} else {
					int num = (Integer) MyThreadLocal.threadLocal.get();
					MyThreadLocal.threadLocal.set(num + 1);
					System.out.println("线程" + name + ": "
							+ MyThreadLocal.threadLocal.get());
					if (i == 3) {
						MyThreadLocal.threadLocal.remove();
					}

				}
				
			}

		}

	}

}
