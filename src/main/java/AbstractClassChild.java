public class AbstractClassChild extends AbstractClass {
    @Override
    public void printString(String str) {
        System.out.println(str);
        super.printString(str);
    }
}
