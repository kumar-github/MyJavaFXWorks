
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

//http://dontpanic.42.nl/2012/05/connecting-to-jvm-programmatically.html
public class Attach
{
	public static void main(String[] args)
	{
		new Attach().showData();
		
	}
    private void showData()
    {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor virtualMachineDescriptor : vms) {
            System.out.println(virtualMachineDescriptor.id() + " ==== " + virtualMachineDescriptor.displayName());
            VirtualMachine virtualMachine = attach(virtualMachineDescriptor);
            if (virtualMachine != null)
            {
            	System.out.println(readSystemProperty(virtualMachine, "java.version"));
            }
        }
    }

    private VirtualMachine attach(VirtualMachineDescriptor virtualMachineDescriptor)
    {
        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
        }
        catch (AttachNotSupportedException anse)
        {
        	System.out.println(anse);
        }
        catch (IOException ioe)
        {
        	System.out.println(ioe);
        }
        finally
        {
            detachSilently(virtualMachine);
        }
        return virtualMachine;
    }

    private String readSystemProperty(VirtualMachine virtualMachine, String propertyName)
    {
        String propertyValue = null;
        try {
            Properties systemProperties = virtualMachine.getSystemProperties();
            propertyValue = systemProperties.getProperty(propertyName);
        } catch (IOException e) {
            System.out.println("Reading system property failed" + e);
        }
        return propertyValue;
    }

    private void detachSilently(VirtualMachine virtualMachine) {
        if (virtualMachine != null) {
            try {
                virtualMachine.detach();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}