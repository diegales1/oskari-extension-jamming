package org.oskari.example;

import fi.nls.oskari.annotation.OskariActionRoute;
import fi.nls.oskari.control.*;
import fi.nls.oskari.log.LogFactory;
import fi.nls.oskari.log.Logger;
import fi.nls.oskari.util.ResponseHelper;


import fi.nls.oskari.map.layer.OskariLayerServiceMybatisImpl;
import fi.nls.oskari.domain.map.OskariLayer;
import java.util.List;
import fi.nls.oskari.map.layer.OskariLayerService;
import fi.nls.oskari.service.OskariComponentManager;



@OskariActionRoute("MyAction")
public class MyActionHandler extends RestActionHandler {

    private static final Logger LOG = LogFactory.getLogger(MyActionHandler.class);

    private static OskariLayerServiceMybatisImpl mapLayerService;



    public void preProcess(ActionParameters params) throws ActionException {
        // common method called for all request methods
        LOG.info(params.getUser(), "accessing route", getName());

        long start = System.currentTimeMillis();

        mapLayerService = new OskariLayerServiceMybatisImpl();

        LOG.info("vamoaca");

        List<OskariLayer> layers = mapLayerService.findAll();

        LOG.info("********** "+layers.size()+" layers read in", System.currentTimeMillis() - start, "ms");

        OskariLayer layer = layers.get(0);

        LOG.info("********** "+layer.getId(), layer.getName(), layer.getStyle());
    }

    @Override
    public void handleGet(ActionParameters params) throws ActionException {
        ResponseHelper.writeResponse(params, "Hola " + params.getUser().getFullName());
    }

    @Override
    public void handlePost(ActionParameters params) throws ActionException {
        throw new ActionException("This will be logged including stack trace");
    }

    @Override
    public void handlePut(ActionParameters params) throws ActionException {
        throw new ActionParamsException("Notify there was something wrong with the params");
    }

    @Override
    public void handleDelete(ActionParameters params) throws ActionException {
        throw new ActionDeniedException("Not deleting anything");
    }


}
