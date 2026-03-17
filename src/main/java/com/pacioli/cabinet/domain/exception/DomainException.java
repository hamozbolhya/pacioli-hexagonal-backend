package com.pacioli.cabinet.domain.exception;

/**
 * Exception de base pour toutes les erreurs métier du domaine Cabinet.
 * On utilise RuntimeException pour éviter de polluer les signatures de méthodes avec des 'throws'.
 */
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}