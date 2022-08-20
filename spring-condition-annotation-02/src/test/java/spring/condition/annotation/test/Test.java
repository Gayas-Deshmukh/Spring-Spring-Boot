package spring.condition.annotation.test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.condition.annoation.config.AppConfig;
import com.spring.condition.annoation.config.UserDao;

public class Test
{

	public static void main(String[] args) 
	{
		ApplicationContext context	=	new AnnotationConfigApplicationContext(AppConfig.class);
		
		UserDao userDao	=	context.getBean(UserDao.class);
		
		System.out.println(userDao.getUses());	
		
		System.out.println("----------------------------------------");
		
		for (String beanName : context.getBeanDefinitionNames())
		{
			System.out.println(beanName);
		}

	}

}
