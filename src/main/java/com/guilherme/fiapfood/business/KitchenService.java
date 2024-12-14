package com.guilherme.fiapfood.business;

import com.guilherme.fiapfood.client.OrderClient;
import com.guilherme.fiapfood.dto.OrderResponseDTO;
import com.guilherme.fiapfood.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final OrderClient orderClient;
    @Scheduled(fixedRate = 120000)
    public void updateOrderByStatusReceivedOrReady() {
        List<OrderResponseDTO> ordersByStatusReceivedOrReady = orderClient.findOrdersByStatusReceivedOrReady();

        Optional<OrderResponseDTO> order = ordersByStatusReceivedOrReady.stream()
                .min(Comparator.comparing(OrderResponseDTO::getDateTimeOrder));

        order.ifPresent(o -> {
            o.setOrderStatus(OrderStatus.IN_PREPARATION);
            orderClient.updateStatusOrder(o.getId(), o.getOrderStatus());
        });
    }


    @Scheduled(fixedRate = 120000)
    public void updateOrderByStatus() {
        List<OrderResponseDTO> ordersByStatus= orderClient.findByOrderStatus(OrderStatus.IN_PREPARATION);

        Optional<OrderResponseDTO> order = ordersByStatus.stream()
                .min(Comparator.comparing(OrderResponseDTO::getDateTimeOrder));

        order.ifPresent(o -> {
            o.setOrderStatus(OrderStatus.FINISHED);
            orderClient.updateStatusOrder(o.getId(), o.getOrderStatus());
        });
    }
}
