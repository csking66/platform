package com.core.util;

import org.springframework.data.domain.AuditorAware;

public class UserAuditor implements AuditorAware<Long> {

	@Override
	public Long getCurrentAuditor() {

		return 1L;
	}
}
