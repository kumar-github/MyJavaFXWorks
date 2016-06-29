import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class ListVM
{
  public static void main(String[] args)
  {
	  List<VirtualMachineDescriptor> vmList = VirtualMachine.list();
	  
	  for(VirtualMachineDescriptor vm : vmList)
	  {
		  System.out.println("name: " + vm.displayName() + " id :" + vm.id());
		  try
		  {
			  VirtualMachine vm0 = VirtualMachine.attach(vm.id());
			  // load agent, agnet class's agentmain will be invoked.
			  //vm0.loadAgent("/root/tmp/ChinaAgent.jar");
			  vm0.loadAgent("c:\\abc.jar");
			  System.out.println("Load agent done.");
			  vm0.detach();
		  }
		  catch(Exception e)
		  {
			  System.out.println("exception : " + e.getMessage());
		  }
	  }
  }
}