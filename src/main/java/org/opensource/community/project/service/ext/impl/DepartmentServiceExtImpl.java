package org.opensource.community.project.service.ext.impl;

import java.util.Date;

import org.opensource.community.project.service.ext.DepartmentServiceExt;
import org.opensource.community.project.service.impl.DepartmentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * The Class DepartmentServiceExtImpl.
 */
@Service
@Transactional
public class DepartmentServiceExtImpl extends DepartmentServiceImpl implements DepartmentServiceExt {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceExtImpl.class);
}