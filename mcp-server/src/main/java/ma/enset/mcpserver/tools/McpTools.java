package ma.enset.mcpserver.tools;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class McpTools {

    @McpTool(name = "getEmployee",
            description = "Get information about a given employee")
    public Employee getEmployee(@McpArg(description = "The employee name") String name) {
        return new Employee(name,12300,5);
    }

    @McpTool(description = "Get All Employees")
    public List<Employee> getAllEmployees() {
        return List.of(
                new Employee("hassan",12300,4),
                new Employee("safa",3342,2),
                new Employee("hicham",4444,1)
        );
    }

    record Employee(String name,double salary,int seniority){}
}
