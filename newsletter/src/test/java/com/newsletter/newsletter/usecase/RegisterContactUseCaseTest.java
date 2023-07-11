package com.newsletter.newsletter.usecase;


import com.newsletter.newsletter.domain.requests.RequestRegister;
import com.newsletter.newsletter.services.ClientEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class RegisterContactUseCaseTest {

    @InjectMocks
    private RegisterContactUseCase registerContactUseCase;

    @Mock
    private ClientEmailService clientEmailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void registerTest(){
        Mockito.doNothing().when(clientEmailService).insertClient(Mockito.any());
        RequestRegister requestRegister = new RequestRegister();
        requestRegister.setName("Name");
        requestRegister.setEmail("name@gmail.com");
        requestRegister.setPhone("1187868878");
        registerContactUseCase.register(
            requestRegister
        );
    }

}
