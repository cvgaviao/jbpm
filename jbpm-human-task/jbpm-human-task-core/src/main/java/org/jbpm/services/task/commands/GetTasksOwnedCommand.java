package org.jbpm.services.task.commands;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kie.api.task.model.Status;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.command.Context;
import org.kie.internal.query.QueryFilter;

@XmlRootElement(name = "get-task-by-owner-command")
@XmlAccessorType(XmlAccessType.NONE)
public class GetTasksOwnedCommand extends UserGroupCallbackTaskCommand<List<TaskSummary>> {

    private static final long serialVersionUID = -1763215272466075367L;

    @XmlElement
    private List<Status> status;
    
    private QueryFilter filter;

    public GetTasksOwnedCommand() {
    }

    public GetTasksOwnedCommand(String userId) {
        this.userId = userId;

    }

    public GetTasksOwnedCommand(String userId, List<Status> status) {
        this.userId = userId;
        this.status = status;
    }
    
    public GetTasksOwnedCommand(String userId, List<Status> status, QueryFilter filter) {
        this.userId = userId;
        this.status = status;
        this.filter = filter;
    }

    public List<Status> getStatus() {
        return status;
    }

    public QueryFilter getFilter() {
        return filter;
    }


    public List<TaskSummary> execute(Context cntxt) {
        TaskContext context = (TaskContext) cntxt;
        doCallbackUserOperation(userId, context);
        doUserGroupCallbackOperation(userId, null, context);
        return context.getTaskQueryService().getTasksOwned(userId, status, filter);
        
    }

}
