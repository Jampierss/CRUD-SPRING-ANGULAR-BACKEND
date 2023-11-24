package com.tevology.logistica.controllers.maestros;

import com.tevology.logistica.models.entity.maestros.Area;
import com.tevology.logistica.models.services.maestros.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AreaController {

    @Autowired
    private IAreaService areaService;

    @GetMapping("/area")
    public List<Area> all() {
        return areaService.findAll();
    }

    @PostMapping("/area")
    public Area save(@RequestBody Area area) {
        return areaService.save(area);
    }

    @GetMapping("/area/filtrar/{term}")
    public List<Area> autocomplete(@PathVariable String term) {
        return areaService.findByNombre(term);
    }
}
