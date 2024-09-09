package io.bootify.agenda_tarefas.tarefa;

import io.bootify.agenda_tarefas.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(final TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("tarefas", tarefaService.findAll());
        return "tarefa/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("tarefa") final TarefaDTO tarefaDTO) {
        return "tarefa/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("tarefa") @Valid final TarefaDTO tarefaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tarefa/add";
        }
        tarefaService.create(tarefaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tarefa.create.success"));
        return "redirect:/tarefas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("tarefa", tarefaService.get(id));
        return "tarefa/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("tarefa") @Valid final TarefaDTO tarefaDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tarefa/edit";
        }
        tarefaService.update(id, tarefaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tarefa.update.success"));
        return "redirect:/tarefas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        tarefaService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("tarefa.delete.success"));
        return "redirect:/tarefas";
    }

    @PostMapping("/complete/{id}")
    public String markCompleted(@PathVariable(name = "id") final Long id) {
        tarefaService.markCompleted(id);
        return "redirect:/tarefas";
    }

}
