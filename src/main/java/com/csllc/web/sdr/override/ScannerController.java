package com.csllc.web.sdr.override;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.csllc.repository.ScannerRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
//@BasePathAwareController
public class ScannerController {

    private final ScannerRepository repository;

    @Inject
    public ScannerController(ScannerRepository repo) {
        repository = repo;
    }

    /**
     * 
     * always hit http://localhost:5199/api/scanners/search/listProducers
     * 
     * using @Controller on this class:
     * - if you comment out this method, hitting this url returns an empty body "{ }" because the SDR implementation using the repository returns no data
     * - if you uncomment this method, hitting the url returns this body instead "["a","b","c"]", so that verifies we are using this implementation
     * 
     * Using @BasePathAwareController
     * - if you use "/api/scanners/search/listProducers" for the @RequestMapping value, it uses this implementation
     * - if you use "/scanners/search/listProducers" for the @RequestMapping value, you get the SDR implementation
     *   although I expected "request mappings to be augmented with a base URI in the Spring Data REST configuration."
     */
    @RequestMapping(value = "/api/scanners/search/listProducers", method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody List<String> getProducers() {
        // use something different from straight repository.listProducers();
        return Arrays.asList("a", "b", "c");
    }

}
