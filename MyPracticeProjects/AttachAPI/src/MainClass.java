import com.sun.tools.attach.VirtualMachine;

public class MainClass
{
	public static void main(String[] args) throws Exception
	{
		VirtualMachine vm = VirtualMachine.attach("18392");
		System.out.println(vm.id());
		vm.loadAgent("c:\\abc.jar", "This is to test.");
		vm.detach();
	}
}