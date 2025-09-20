package PaymentProcessingPipeline;

enum LogLevel{
    FRAUD, WALLET, GIFT, FALL_BACK
}

interface HandlerOutput{
    public void write();
}

class ConsoleOutput implements HandlerOutput{

    @Override
    public void write() {
        System.out.println("this is console output");
    }
}

abstract class HandlerProcessor{
    HandlerProcessor nextProcessor;
    HandlerOutput output = new ConsoleOutput();

    public HandlerProcessor setNext(HandlerProcessor handlerProcessor){
        this.nextProcessor = handlerProcessor;
        return nextProcessor;
    }

    public void log(LogLevel logLevel, String message){
        if(!handle(logLevel, message) && nextProcessor != null){
            nextProcessor.log(logLevel, message);
        }else{
            output.write();
        }
    }

    public abstract boolean handle(LogLevel logLevel, String message);

    public void fraud(String message){
        nextProcessor.log(LogLevel.FRAUD, message);
    }

    public void walletPayment(String message){
        nextProcessor.log(LogLevel.WALLET, message);
    }

    public void giftCard(String message){
        nextProcessor.log(LogLevel.GIFT, message);
    }

    public void fallback(String message){
        nextProcessor.log(LogLevel.FALL_BACK, message);
    }

}

class FraudHandler extends HandlerProcessor{

    @Override
    public boolean handle(LogLevel logLevel, String message) {
        return LogLevel.FRAUD == logLevel;
    }
}

class WalletHandler extends HandlerProcessor{

    @Override
    public boolean handle(LogLevel logLevel, String message) {
        return LogLevel.WALLET == logLevel;
    }
}

class GiftHandler extends HandlerProcessor{

    @Override
    public boolean handle(LogLevel logLevel, String message) {
        return LogLevel.GIFT == logLevel;
    }
}

class FallBack extends HandlerProcessor{

    @Override
    public boolean handle(LogLevel logLevel, String message) {
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        HandlerProcessor fraud = new FraudHandler();
        HandlerProcessor wallet = new WalletHandler();
        HandlerProcessor gift = new GiftHandler();
        HandlerProcessor fallback = new FallBack();

        HandlerProcessor handlerProcessor = fraud.setNext(wallet).setNext(gift).setNext(fallback);

        handlerProcessor.log(LogLevel.WALLET, "wallet");
    }
}


/*

Problem:
Design and implement a payment processing pipeline for an e-commerce platform using the Chain of Responsibility pattern. When a customer attempts to pay for an order, the system should try to process the payment in a predefined sequence of handlers. Each handler either:

Processes the request (and stops the chain), or

Defers to the next handler in the chain.

Requirements:

The pipeline must support the following handlers:

FraudCheckHandler: Performs basic fraud checks (e.g., amount threshold, country risk). If the payment fails fraud checks, stop the chain and mark as rejected.

WalletPaymentHandler: If the user has sufficient wallet balance, debit wallet and approve.

GiftCardHandler: If a valid gift card is provided and has sufficient balance, deduct and approve.

CardPaymentHandler: Attempt to charge the credit/debit card (assume a mock gateway call). If approved, stop the chain.

FallbackHandler: If none of the above can process, mark as failed/insufficient funds.

The order of handlers should be configurable at runtime (e.g., Fraud -> Wallet -> GiftCard -> Card -> Fallback or Fraud -> GiftCard -> Wallet -> Card -> Fallback).

 */