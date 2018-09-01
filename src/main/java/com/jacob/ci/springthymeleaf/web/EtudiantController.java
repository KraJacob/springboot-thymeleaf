package com.jacob.ci.springthymeleaf.web;

import com.jacob.ci.springthymeleaf.dao.EtudiantRepository;
import com.jacob.ci.springthymeleaf.entity.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/Etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantRepository etudiantRepository;

    @RequestMapping(value = "/index")
    private String index(Model model,@RequestParam(name = "page", defaultValue = "0") int p,
                         @RequestParam(name = "motCle", defaultValue = "") String mc)
    {
        Page<Etudiant> etudiants = etudiantRepository.findByNomLike("%"+mc+'%',new PageRequest(p,4));
        int total = etudiants.getTotalPages();
        int [] pages = new int[total];
        for (int i=0;i<total;i++) pages[i] = i;
        model.addAttribute("etudiants",etudiants);
        model.addAttribute("pages",pages);
        model.addAttribute("pageCourante",p);
        model.addAttribute("motCle",mc);
        return "etudiant";
    }
}
