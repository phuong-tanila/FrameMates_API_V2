package fu.training.FrameMates_BE.views;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/api/views")
public class ViewController {
    @Autowired
    private ViewRepository viewRepository;
    @GetMapping
    public ResponseEntity<View> getViews(){
        return ResponseEntity.ok(viewRepository.findById(1).get());
    }

    @PutMapping("web")
    public ResponseEntity increaseWebView(){
        View currentView = viewRepository.findById(1).get();
        currentView.setWebView(currentView.getWebView() + 1);
        viewRepository.save(currentView);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("app")
    public ResponseEntity increaseAppDownload(){
        View currentView = viewRepository.findById(1).get();
        currentView.setAppDownload(currentView.getAppDownload() + 1);
        viewRepository.save(currentView);
        return ResponseEntity.noContent().build();
    }
}
