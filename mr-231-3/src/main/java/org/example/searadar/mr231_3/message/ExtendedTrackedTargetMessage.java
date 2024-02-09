package org.example.searadar.mr231_3.message;

import ru.oogis.searadar.api.message.TrackedTargetMessage;

/**
 * Расширенный класс TTM.
 *
 * <p>Добавлено целочисленное поле - интервал,
 * которое содержит интервал времени в миллисекундах между сигналом «Север»,
 * поступающим в изделие по каналу аналоговой информации,
 * и моментом зондирования цели НРЛС.</p>
 *
 * @author Вадим Комиссаров
 * @version 1.0
 */
public class ExtendedTrackedTargetMessage extends TrackedTargetMessage {
    /** Поле интервал */
    private Integer interval;

    /**
     * Метод определения интервала {@link ExtendedTrackedTargetMessage#interval}
     * @param interval интервал
     */
    public void setInterval(Integer interval) { this.interval = interval; }

    /**
     * Метод получения значения поля {@link ExtendedTrackedTargetMessage#interval}
     * @return целочисленное значение интервала в миллисекундах
     */
    public Integer getInterval() { return interval; }

    /**
     * Метод для формирования TTM сообщения
     * @return TTM сообщение в строковом представлении
     */
    @Override
    public String toString() {
        return "TrackedTargetMessage{" +
                "msgRecTime=" + getMsgRecTime() +
                ", msgTime=" + getMsgTime() +
                ", targetNumber=" + getTargetNumber() +
                ", distance=" + getDistance() +
                ", bearing=" + getBearing() +
                ", course=" + getCourse() +
                ", speed=" + getSpeed() +
                ", type=" + getType() +
                ", status=" + getStatus() +
                ", iff=" + getIff() +
                ", interval=" + getInterval() +
                '}';
    }
}
