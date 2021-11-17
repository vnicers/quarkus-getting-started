package org.acme.getting.started.common.validator;

import javax.validation.groups.Default;

public interface ValidationGroups {
    interface Post extends Default {
    }
    interface Put extends Default { 
    }
}