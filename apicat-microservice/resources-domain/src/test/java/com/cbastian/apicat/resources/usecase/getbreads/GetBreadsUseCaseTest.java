package com.cbastian.apicat.resources.usecase.getbreads;

import com.cbastian.apicat.resources.kernel.command.getbreads.GetBreadsCommand;
import com.cbastian.apicat.resources.kernel.domain.getbreads.GetBreadsInformation;
import com.cbastian.apicat.resources.kernel.domain.util.models.CatBreadItem;
import com.cbastian.apicat.resources.kernel.domain.util.models.Weight;
import com.cbastian.apicat.resources.kernel.exception.error.UseCaseErrorResponse;
import com.cbastian.apicat.resources.ports.webclients.TheCatApiClientPort;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GetBreadsUseCaseTest {
    private final TheCatApiClientPort theCatApiClientPort = Mockito.mock(TheCatApiClientPort.class);
    private final GetBreadsUseCase getBreadsUseCase = new GetBreadsUseCase(theCatApiClientPort);

    @Test
    public void testExecute() {
        // Crear un comando de prueba
        GetBreadsCommand command = GetBreadsCommand
                .builder()
                .messageUuid("asdf-asdf-gasd-fasd")
                .authorization("234njkwfhnjkewhfkdkjdf")
                .build();

        // Crear un objeto de prueba
        CatBreadItem cardItem = CatBreadItem
                .builder()
                .id("1")
                .name("Abyssinian")
                .description("The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.")
                .temperament("Active, Energetic, Independent, Intelligent, Gentle")
                .life_span("14 - 15")
                .wikipedia_url("https://en.wikipedia.org/wiki/Abyssinian_(cat)")
                .origin("Egypt")
                .weight(Weight
                        .builder()
                        .imperial("7  - 10")
                        .metric("3 - 5")
                        .build()
                )
                .reference_image_id("0XYvRd7oD")
                .rare(0)
                .rex(0)
                .shedding_level(2)
                .natural(1)
                .dog_friendly(4)
                .child_friendly(4)
                .country_code("EG")
                .affection_level(5)
                .adaptability(5)
                .cat_friendly(4)
                .country_codes("EG")
                .vocalisation(1)
                .intelligence(5)
                .energy_level(5)
                .experimental(0)
                .health_issues(2)
                .social_needs(5)
                .stranger_friendly(5)
                .grooming(1)
                .hairless(0)
                .hypoallergenic(0)
                .indoor(0)
                .short_legs(0)
                .suppressed_tail(0)
                .short_legs(0)
                .suppressed_tail(0)
                .build();
        // Configurar el puerto mockeado para devolver un flujo de razas de gato cuando se llama a getBreads
        when(theCatApiClientPort.getBreads()).thenReturn(Flux.just(cardItem));

        // Llamar al método execute
        Either<UseCaseErrorResponse, GetBreadsInformation> result = getBreadsUseCase.execute(command);

        // Verificar que el método getBreads del puerto se llamó una vez
        verify(theCatApiClientPort, times(1)).getBreads();

        // Asegurarse de que el resultado es el flujo de razas de gato
        assertNotNull(result.get().getData(), "El campo data es nulo");
        assertTrue(result.get().getData().getBreads().stream().allMatch(bread -> bread instanceof CatBreadItem), "No todos los elementos en el flujo son de tipo CatBreadItem");
    }
}