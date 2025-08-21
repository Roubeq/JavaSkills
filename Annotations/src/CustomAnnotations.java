import java.lang.annotation.*;

public class CustomAnnotations {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AnnWithoutParam {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    public @interface AnnWithParam {
        String name();
        int version() default 1;
        String[] array() default {"Alyosha" ,"Batman"};
    }

    @Inherited // наследование аннотации
    @Target(ElementType.TYPE) // только для TYPE!(интерфейсы, классы, перечисления ...)
    public @interface AnnClass {}


    public @interface Roles {
        Role[] value();
    }

    @Repeatable(Roles.class)
    public @interface Role {
        String value();
    }

    @Role("programmer")
    @Role("teacher")
    @AnnClass
    public class Man {

    }

    public class Child extends Man {
        // наследует ту самую инхеритед
    }


    @AnnWithoutParam()
    @AnnWithParam(
            name = "boba",
            array = {"biba"}
    )
    public static void main(String[] args) {

    }
}
