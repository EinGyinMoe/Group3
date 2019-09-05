package com.napier.sem;

/**
 * Represents a language
 */
public class Language {
    public String getCountryCode() {
        return CountryCode;
    }


    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        IsOfficial = isOfficial;
    }

    public Float getPercentage() {
        return Percentage;
    }

    public void setPercentage(Float percentage) {
        Percentage = percentage;
    }

    private String CountryCode;

    private String Language;

    private String IsOfficial;

    private Float Percentage;

    public void setCountry(Country country) {
       this.country = country;
    }

    public Country getCountry() {
        return null;
    }
}
