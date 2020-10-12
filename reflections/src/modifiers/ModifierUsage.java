package modifiers;

import java.lang.reflect.Modifier;


public class ModifierUsage {
	public static void main(String[] args) throws Exception {

		//Entity e = new Entity(23, "jai shree ram");
		//Class<Entity> classEntity = Entity.class;
		
		Class<?> classEntity = Class.forName("common.Entity");
		
		//chekcing if class is public
		int classModifier = classEntity.getModifiers();
		int classModiferRes = classModifier & Modifier.PUBLIC;
		//int classModiferRes = classModifier & Modifier.PRIVATE;
		
		//prints 0 for non public and 1 for public
		System.out.println("is class public "+classModiferRes);
		System.out.println("boolean is class public "+Modifier.isPublic(classModifier));
		
		//field check -> uisng all public and private fields both
		int modifiersField= classEntity.getDeclaredField("value").getModifiers();
		int modifiersFieldRes = modifiersField & Modifier.PUBLIC;
		System.out.println("is field public "+modifiersFieldRes);
		System.out.println("boolena is field public "+Modifier.isPublic(modifiersField));
		
		//private fields
		int modifiersField1= classEntity.getDeclaredField("name").getModifiers();
		int modifiersFieldRes1 = modifiersField1 & Modifier.PUBLIC;
		System.out.println("is field public "+modifiersFieldRes1);
		System.out.println("boolena is field public "+Modifier.isPublic(modifiersField1));
		
		//both public and static
		int modifiersField2= classEntity.getDeclaredField("static_property").getModifiers();
		int modifiersFieldRes2 = modifiersField2  & Modifier.STATIC;
		//will return 1 only if both public and static , otherwise 0
		System.out.println("is field static "+modifiersFieldRes2);
		System.out.println("boolena is field public "+(Modifier.isPublic(modifiersField2) && Modifier.isStatic(modifiersField2)));
		
		//convert modifier to string
		System.out.println("staitc and public modifiers "+Modifier.toString(modifiersField2));
	}
}
