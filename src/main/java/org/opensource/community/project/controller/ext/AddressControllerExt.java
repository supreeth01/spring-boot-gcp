package org.opensource.community.project.controller.ext;

import java.util.Date;

import org.opensource.community.project.controller.AddressController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * The Class AddressControllerExt.
 */
@RestController
@RequestMapping(value = "/api/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressControllerExt extends AddressController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AddressControllerExt.class);
}