package dk.kinoxp.kinoxp.dto;

import java.util.List;

public class ReservationRequestDTO {
        private String customerName;
        private String customerEmail;
        private Long screeningId;
        private List<Long> seatId;

        public ReservationRequestDTO(String customerName, String customerEmail, Long screeningId, List <Long> seatId) {
            this.customerName = customerName;
            this.customerEmail = customerEmail;
            this.screeningId = screeningId;
            this.seatId = seatId;
        }


        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }
        public String getCustomerEmail() { return customerEmail; }
        public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
        public Long getScreeningId() { return screeningId; }
        public void setScreeningId (Long screeningId) { this.screeningId = screeningId; }
        public List<Long> getSeatId() { return seatId;}
        public void setSeatId(List<Long> seatId) { this.seatId = seatId;}
}