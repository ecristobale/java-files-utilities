package com.ecristobale.jscommunication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class LoadJS {

	public static void main(String[] args) throws ScriptException, IOException, NoSuchMethodException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		
		String scriptFileAbsolutePath = Paths.get("").toAbsolutePath() + "\\src\\sample.js";
		
		// 1. Read script file
		engine.eval(Files.newBufferedReader(Paths.get(scriptFileAbsolutePath), StandardCharsets.UTF_8));
		
		/* For relative path in a webapp
		URL fileUrl = getClass().getResource("js/SampleWebWorker.js"); 
		engine.eval(Files.newBufferedReader(Paths.get(fileUrl.toURI()),StandardCharsets.UTF_8));
		*/
		
		Invocable inv = (Invocable) engine;
		
		System.out.println("1. [Java] Before call the JS function.");
		
		// 2. Call JS function from script file
		Object result = inv.invokeFunction("sampleFunction", "ARGUMENT01");
		
		System.out.println("4. [Java] Return of the JS Function: " + result);
	}

}
