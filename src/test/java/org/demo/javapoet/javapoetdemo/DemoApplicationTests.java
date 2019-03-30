package org.demo.javapoet.javapoetdemo;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGenerateHelloWorld() throws IOException {
		MethodSpec main = main();

		MethodSpec hello = hello();

		MethodSpec compute = computeRange("multiply10to20", 10, 20, "*");

		MethodSpec currentSpecMillis = currentSpecMillis();

		MethodSpec catchException = catchException();

		MethodSpec today = today();

		MethodSpec beyond = beyond();

		ClassName nameBoards = ClassName.get("com.example", "Hoverboard", "Boards");

		ClassName hoverboard = ClassName.get("com.example", "Hoverboard");
		MethodSpec beyond2 = beyond2(nameBoards, hoverboard);

		MethodSpec byteToHex = byteToHex();

		CodeBlock.builder().add("I ate $L $L ", 3, "tacos");
		CodeBlock.builder().add("I ate $2L $1L", "tacos", 3);
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("food", "tacos");
		map.put("count", 3);
		CodeBlock.builder().addNamed("I ate $count:L $food:L", map);

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld").addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(main).addMethod(compute).addMethod(hello).addMethod(currentSpecMillis)
				.addMethod(catchException).addMethod(today).addMethod(beyond).addMethod(beyond2).addMethod(byteToHex)
				.build();

		JavaFile toJavaFile = JavaFile.builder("com.example.helloworld", helloWorld)
				.addStaticImport(hoverboard, "createNimbus").addStaticImport(nameBoards, "*")
				.addStaticImport(Collections.class, "*").build();
		toJavaFile.writeTo(System.out);

		MethodSpec flux = MethodSpec.methodBuilder("flux").addModifiers(Modifier.ABSTRACT, Modifier.PROTECTED).build();

		MethodSpec dismiss = MethodSpec.methodBuilder("dismiss")
				.addJavadoc("Hides {@code message} from the caller's history. Other\n"
						+ "participants in the conversation will continue to see the\n"
						+ "message in theire own history unless they also delete it.\n")
				.addJavadoc("\n")
				.addJavadoc("<p>Use{@link #delete($T)} to delete the entire\n" + "conversation for all participants.\n",
						ConversionService.class)
				.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).addParameter(Messager.class, "message").build();

		TypeSpec abstractHelloWorld = TypeSpec.classBuilder("AbstractHelloWorld")
				.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).addMethod(flux).build();

		JavaFile toJavaFile2 = JavaFile.builder("com.example.helloworld", abstractHelloWorld).build();
		toJavaFile2.writeTo(System.out);

		System.out.println();

		MethodSpec fluxSpec = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC)
				.addParameter(String.class, "greeting").addStatement("this.$N = $N", "greeting", "greeting").build();

		ParameterSpec android = ParameterSpec.builder(String.class, "android").addModifiers(Modifier.FINAL).build();

		MethodSpec welcomeOverlords = MethodSpec.methodBuilder("welcomeOverlords").addParameter(android)
				.addParameter(String.class, "roboot", Modifier.FINAL).build();

		FieldSpec ant = FieldSpec.builder(String.class, "ant").addModifiers(Modifier.PRIVATE, Modifier.FINAL)
				.initializer("$S + $L", "Lolipop v.", 5.0d) // 初始化
				.build();

		TypeSpec fluxType = TypeSpec.classBuilder("Flux").addModifiers(Modifier.PUBLIC)
				.addField(String.class, "greeting", Modifier.PRIVATE, Modifier.FINAL).addMethod(fluxSpec)
				.addMethod(welcomeOverlords).addField(ant).build();

		JavaFile toJavaFile3 = JavaFile.builder("com.example.helloworld", fluxType).build();
		toJavaFile3.writeTo(System.out);

		System.out.println();

		TypeSpec myInterface = TypeSpec.interfaceBuilder("IMyInterface").addModifiers(Modifier.PUBLIC)
				.addField(FieldSpec.builder(String.class, "ONLY_THING_THAT_IS_CONSTANT")
						.addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL).initializer("$S", "change")
						.build())
				.addMethod(MethodSpec.methodBuilder("beep").addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT).build())
				.build();

		JavaFile toJavaFile4 = JavaFile.builder("com.example.helloworld", myInterface).build();
		toJavaFile4.writeTo(System.out);

		TypeSpec myEnum = TypeSpec.enumBuilder("MyEnum").addModifiers(Modifier.PUBLIC).addEnumConstant("ROCK")
				.addEnumConstant("SCISSORS").addEnumConstant("PAPER").build();
		JavaFile toJavaFile5 = JavaFile.builder("com.example.helloworld", myEnum).build();
		toJavaFile5.writeTo(System.out);

		TypeSpec myEnum2 = TypeSpec.enumBuilder("MyEnum2").addModifiers(Modifier.PUBLIC)
				.addEnumConstant("ROCK",
						TypeSpec.anonymousClassBuilder("$S", "first")
								.addMethod(MethodSpec.methodBuilder("toString").addAnnotation(Override.class)
										.addModifiers(Modifier.PUBLIC).addStatement("return $S", "avalance!")
										.returns(String.class).build())
								.build())
				.addEnumConstant("SCISSORS", TypeSpec.anonymousClassBuilder("$S", "peace").build())
				.addEnumConstant("PAPER", TypeSpec.anonymousClassBuilder("$S", "flat").build())
				.addField(String.class, "handsign", Modifier.PRIVATE, Modifier.FINAL)
				.addMethod(MethodSpec.constructorBuilder().addParameter(String.class, "handsign")
						.addStatement("this.$N = $N", "handsign", "handsign").build())
				.build();
		JavaFile toJavaFile6 = JavaFile.builder("com.example.helloworld", myEnum2).build();
		toJavaFile6.writeTo(System.out);

		TypeSpec comparator = TypeSpec.anonymousClassBuilder("")
				.addSuperinterface(ParameterizedTypeName.get(Comparator.class, String.class))
				.addMethod(MethodSpec.methodBuilder("compare").addAnnotation(Override.class)
						.addModifiers(Modifier.PUBLIC).addParameter(String.class, "a").addParameter(String.class, "b")
						.returns(int.class).addStatement("return $N.length() - $N.length()", "a", "b").build())
				.build();

		MethodSpec logRecord = MethodSpec.methodBuilder("recordEvent")
				.addModifiers(Modifier.PUBLIC/* , Modifier.ABSTRACT */)
				.addAnnotation(AnnotationSpec.builder(HttpHeaders.class)
						.addMember("accept", "$S", "application/json; charset=utf-8")
						.addMember("userAgent", "$S", "Square Cash").build())
				.addParameter(LogRecord.class, "logRecord").addStatement("return null").returns(LogRecord.class)
				.build();

		MethodSpec logRecord2 = MethodSpec.methodBuilder("recordEvent2").addModifiers(Modifier.PUBLIC)
				.addAnnotation(AnnotationSpec.builder(HttpHeaders.class)
						.addMember("value", "$L",
								AnnotationSpec.builder(HttpHeaders.class).addMember("name", "$S", "Accept")
										.addMember("value", "$S", "application/json; charset=utf-8").build())
						.addMember("value", "$L",
								AnnotationSpec.builder(HttpHeaders.class).addMember("name", "$S", "User-Agent")
										.addMember("value", "$S", "Square Cash").build())
						.build())
				.addParameter(LogRecord.class, "logRecord").returns(LogRecord.class).build();

		TypeSpec anonymous = TypeSpec.classBuilder("MyAnonymous")
				.addMethod(MethodSpec.methodBuilder("sortByLenght")
						.addParameter(ParameterizedTypeName.get(List.class, String.class), "strings")
						.addStatement("$T.sort($N, $L)", Collections.class, "strings", comparator).build())
				.addMethod(logRecord).addMethod(logRecord2).build();

		JavaFile toJavaFile7 = JavaFile.builder("com.example.helloworld", anonymous).build();
		toJavaFile7.writeTo(System.out);

	}

	private MethodSpec byteToHex() {
		MethodSpec hexDigit = MethodSpec.methodBuilder("hexDigit").addParameter(int.class, "i").returns(char.class)
				.addStatement("return (char)(i < 10 ) ? i + '0' : i - 10 + 'a')").build();
		MethodSpec byteToHex = MethodSpec.methodBuilder("byteToHex").addParameter(int.class, "b").returns(String.class)
				.addStatement("char[] result = new char[2]").addStatement("result[0] = $N((b >>> 4) & 0xf)", hexDigit)
				.addStatement("result[1] = $N(b & 0xf", hexDigit).addStatement("return new String(result)").build();
		return byteToHex;
	}

	private MethodSpec beyond2(ClassName nameBoards, ClassName hoverboard) {
		ClassName list = ClassName.get("java.util", "List");
		ClassName arrayList = ClassName.get("java.util", "ArrayList");
		TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);
		MethodSpec beyond2 = MethodSpec.methodBuilder("beyond2").returns(listOfHoverboards)
				.addStatement("$T result = new $T<>()", listOfHoverboards, arrayList)
				.addStatement("result.add($T.createNimbus(2000))", hoverboard)
				.addStatement("result.add($T.createNimbus(\"2001\"))", hoverboard)
				.addStatement("result.add($T.createNimbus($T.THUNDERBOLT)", hoverboard, nameBoards)
				.addStatement("$T.sort(result)", Collections.class)
				.addStatement("return result.isEmpty() ? $T.emptyList(): result", Collections.class)
				.addStatement("return result").build();
		return beyond2;
	}

	private MethodSpec beyond() {
		ClassName hoverboard = ClassName.get("com.example", "Hoverboard");
		ClassName list = ClassName.get("java.util", "List");
		ClassName arrayList = ClassName.get("java.util", "ArrayList");

		TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);

		MethodSpec beyond = MethodSpec.methodBuilder("beyond").returns(listOfHoverboards)
				.addStatement("$T result = new $T<>()", listOfHoverboards, arrayList)
				.addStatement("result.add(new $T())", hoverboard).addStatement("result.add(new $T())", hoverboard)
				.addStatement("result.add(new $T())", hoverboard).addStatement("result.add(new $T())", hoverboard)
				.addStatement("return result").build();
		return beyond;
	}

	private MethodSpec today() {
		ClassName hoverboard = ClassName.get("com.example", "Hoverboard");
		MethodSpec today = MethodSpec.methodBuilder("tomorrow").returns(hoverboard)
				.addStatement("return new $T()", hoverboard).build();
		return today;
	}

	private MethodSpec catchException() {
		MethodSpec catchException = MethodSpec.methodBuilder("catchException").beginControlFlow("try")
				.addStatement("throw new Exception($S)", "Failed").nextControlFlow("catch ($T e)", Exception.class)
				.addStatement("throw new $T(e)", RuntimeException.class).endControlFlow().build();
		return catchException;
	}

	private MethodSpec hello() {
		MethodSpec hello = MethodSpec.methodBuilder("hello").addStatement("int total = 0")
				.beginControlFlow("for (int i = 0; i < 10; i++)").addStatement("total += i").endControlFlow().build();
		return hello;
	}

	private MethodSpec main() {
		MethodSpec main = MethodSpec.methodBuilder("main").addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.returns(Void.class).addParameter(String[].class, "args")
				.addStatement("$T.out.println($S", System.class, "Hello, JavaPoet!").build();
		return main;
	}

	private MethodSpec currentSpecMillis() {
		MethodSpec currentSpecMillis = MethodSpec.methodBuilder("currentSpecMills")
				.addStatement("long now = $T.currentTimeMills()", System.class)
				.beginControlFlow("if ($T.currentTimeMills() < now) ", System.class)
				.addStatement("$T.out.println($S)", System.class, "Time travelling, woo hoo!")
				.nextControlFlow("else if($T.currentTimeMills() == now)", System.class)
				.addStatement("$T.out.println($S)", System.class, "Time stood still!").nextControlFlow("else")
				.addStatement("$T.out.println($S)", System.class, "Ok, time still moving forward").endControlFlow()
				.build();
		return currentSpecMillis;
	}

	private MethodSpec computeRange(String name, int from, int to, String op) {
		return MethodSpec.methodBuilder(name).returns(int.class).addStatement("int result = 1")
				.beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
				.addStatement("result = result " + op + " i").endControlFlow().addStatement("return result").build();
	}

}
