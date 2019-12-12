package org.opensource.community.project.service.ext.impl;

import java.util.Date;

import org.opensource.community.project.service.ext.EmployeeServiceExt;
import org.opensource.community.project.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * The Class EmployeeServiceExtImpl.
 */
@Service
@Transactional
public class EmployeeServiceExtImpl extends EmployeeServiceImpl implements EmployeeServiceExt {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceExtImpl.class);
}