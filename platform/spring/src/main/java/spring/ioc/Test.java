package spring.ioc;

import org.springframework.util.StopWatch;

public class Test {

	public static void main(String[] args) {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		 System.out.println(Thread.currentThread().getContextClassLoader().getResource(Test.class.getName()));
		
		// 此时三个ClassLoader是同一个对象
        System.out.println(Thread.currentThread().getContextClassLoader()); // 当前线程的类加载器
        System.out.println(Test.class.getClassLoader()); // 当前类的类加载器
        System.out.println(ClassLoader.getSystemClassLoader()); // 系统初始的类加载器
        
	}

}
