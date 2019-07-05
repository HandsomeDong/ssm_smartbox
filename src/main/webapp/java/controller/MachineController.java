package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.impl.BoxServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/machine")
public class MachineController {
    @Autowired
    private BoxServiceImpl boxService;

    @RequestMapping(value = "/box", method = RequestMethod.GET)
    public Map getEmptyBoxes(){
        Map<String, Object> result = new HashMap<>();
        int[] emptyBoxesIds = boxService.getEmptyBoxes();
        result.put("data", emptyBoxesIds);
        return result;
    }
}
