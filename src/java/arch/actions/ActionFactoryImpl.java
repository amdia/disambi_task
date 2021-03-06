package arch.actions;

import org.ros.node.topic.Publisher;

import arch.actions.internal.Disambiguate;
import arch.actions.internal.GetSparqlVerba;
import arch.actions.robot.PointClose;
import arch.actions.robot.Say;
import arch.actions.ros.StartROSNode;
import jason.asSemantics.ActionExec;
import rjs.arch.actions.AbstractActionFactory;
import rjs.arch.actions.Action;
import rjs.arch.actions.GetHATPPlan;
import rjs.arch.actions.ros.ConfigureNode;
import rjs.arch.actions.ros.InitServices;
import rjs.arch.actions.ros.RetryInitServices;
import rjs.arch.actions.ros.StartParameterLoaderNode;
import rjs.arch.agarch.AbstractROSAgArch;

public class ActionFactoryImpl extends AbstractActionFactory {
	
	private static Publisher<std_msgs.String> sayPub;
	
	public void setRosVariables() {
		super.setRosVariables();
		sayPub = createPublisher("supervisor/topics/say");
	}
	
	public Action createAction(ActionExec actionExec, AbstractROSAgArch rosAgArch) {
		String actionName = actionExec.getActionTerm().getFunctor();
		Action action = null;
		switch(actionName) {
			case "disambiguate":
				action = new Disambiguate(actionExec, rosAgArch);
				break;
			case "sparql_verbalization":
				action = new GetSparqlVerba(actionExec, rosAgArch);
				break;
			case "say":
				action = new Say(actionExec, rosAgArch, sayPub);
				break;
			case "pointObject":
				action = new PointClose(actionExec,rosAgArch);
				break;
			case "getHatpPlan":
				action = new GetHATPPlan(actionExec, rosAgArch);
				break;
			case "configureNode":
				action = new ConfigureNode(actionExec, rosAgArch);
				break;
			case "startParameterLoaderNode":
				action = new StartParameterLoaderNode(actionExec, rosAgArch);
				break;
			case "startROSNode":
				action = new StartROSNode(actionExec, rosAgArch);
				break;
			case "initServices":
				action = new InitServices(actionExec, rosAgArch);
				break;
			case "retryInitServices":
				action = new RetryInitServices(actionExec, rosAgArch);
				break;
			default:
				break;
		}
			
		return action;
	}
	
}
