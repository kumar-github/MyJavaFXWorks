import java.lang.management.ManagementFactory;
import java.util.List;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

public class JVMInfo
{
	public static void main(String[] args)
	{
		
	List<VirtualMachineDescriptor> descriptors = VirtualMachine.list();
	    
	for (VirtualMachineDescriptor descriptor : descriptors) {
	        System.out.println("Found JVM: " + descriptor.displayName());
	        try {
	            VirtualMachine vm = VirtualMachine.attach(descriptor);
	            String version = vm.getSystemProperties().getProperty("java.runtime.version");
	            System.out.println("   Runtime Version: " + version);
	
	            String connectorAddress = vm.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
	            if (connectorAddress == null) {
	                vm.startLocalManagementAgent();
	                connectorAddress = vm.getAgentProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
	            }
	
	            JMXServiceURL url = new JMXServiceURL(connectorAddress);
	            JMXConnector connector = JMXConnectorFactory.connect(url);
	            MBeanServerConnection mbs = connector.getMBeanServerConnection();
	
	            ObjectName threadName = new ObjectName(ManagementFactory.THREAD_MXBEAN_NAME);
	            Integer threadCount = (Integer)mbs.getAttribute(threadName, "ThreadCount");
	            System.out.println("    Thread count: " + threadCount);
	        }
	        catch (Exception e)
	        {
	        }
	}
	}
}