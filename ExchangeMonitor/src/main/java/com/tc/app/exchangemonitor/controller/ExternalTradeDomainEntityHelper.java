package com.tc.app.exchangemonitor.controller;

import com.tc.app.exchangemonitor.model.ExternalComment;

public interface ExternalTradeDomainEntityHelper extends DomainEntity
{
	ExternalComment externalComment = null;
	
	default public ExternalComment getExternalComment()
	{
		return externalComment;
	}
}