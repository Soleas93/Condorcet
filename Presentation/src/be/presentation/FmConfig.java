package be.presentation;

import java.io.File;
import java.io.IOException;


import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class FmConfig {
	private static final FmConfig instance = new FmConfig();
	private final Configuration cfg;
	
	private FmConfig() {
		cfg = new Configuration(Configuration.VERSION_2_3_30);
		
		try {
			cfg.setDirectoryForTemplateLoading(new File("C:/dev/java/projects/presentation/Presentation"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cfg.setDefaultEncoding("UTF-8");

		// Sets how errors will appear.
		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
		cfg.setLogTemplateExceptions(false);

		// Wrap unchecked exceptions thrown during template processing into TemplateException-s:
		cfg.setWrapUncheckedExceptions(true);

		// Do not fall back to higher scopes when reading a null loop variable:
		cfg.setFallbackOnNullLoopVariable(false);
		
	}
	
	public static final Configuration getConfiguration() 
    {
        return instance.cfg;
    }
}


