package org.opensource.community.project.service.ext.impl;

import java.util.Date;

import org.opensource.community.project.service.ext.AddressServiceExt;
import org.opensource.community.project.service.impl.AddressServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * The Class AddressServiceExtImpl.
 */
@Service
@Transactional
public class AddressServiceExtImpl extends AddressServiceImpl implements AddressServiceExt {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceExtImpl.class);
}