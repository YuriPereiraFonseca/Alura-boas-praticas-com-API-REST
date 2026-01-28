package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    // esse tipo de exceção em qualquer controller do código chamará esse metodo
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        // .notFound() é o erro 404
        return ResponseEntity.notFound().build();
    }

    // exceção para campo inválido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        // lista com os campos que deram erro
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValiacao::new).toList());
    }

    private record DadosErroValiacao(String campo, String mensagem) {
        public DadosErroValiacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
