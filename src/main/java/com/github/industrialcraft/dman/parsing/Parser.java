package com.github.industrialcraft.dman.parsing;

import com.github.industrialcraft.dman.Regex;
import io.vavr.control.Either;

import java.util.HashMap;
import java.util.List;

public class Parser {
    private HashMap<String,ParsationData> parsationDataRegistry;
    private boolean compiled;
    public Parser() {
        this.parsationDataRegistry = new HashMap<>();
        this.compiled = false;
    }
    public void parse(String input){
        ParsationData parsationData = parsationDataRegistry.get("main");
        if(parsationData == null)
            throw new IllegalStateException("Aggregate 'main' not found");

    }
    public class ParseOutput{
        public final String name;
        public final Either<ParseOutput,String> value;
        public ParseOutput(String name, Either<ParseOutput, String> value) {
            this.name = name;
            this.value = value;
        }
    }
    public void register(String name, TokenType... tokenTypes){
        if(parsationDataRegistry.containsKey(name))
            throw new IllegalStateException(name + " already registered inside parser");
        this.parsationDataRegistry.put(name, new ParsationData(List.of(tokenTypes)));
    }
    public void compile(){
        if(this.compiled)
            return;
        this.compiled = true;
        for(ParsationData parsationData : parsationDataRegistry.values())
            parsationData.tokens.forEach(tokenType -> tokenType.compile());
    }

    private class ParsationData {
        public final List<TokenType> tokens;
        public ParsationData(List<TokenType> tokens) {
            this.tokens = tokens;
        }
    }
    public TokenType tokenRegex(Regex regex){
        return new RegexToken(regex);
    }
    public TokenType tokenAggregate(String name){
        return new AggregateToken(name);
    }
    public TokenType tokenChoice(TokenType... tokens){
        return new ChoiceToken(List.of(tokens));
    }
    public class TokenType{
        public void compile(){}
    }
    private class RegexToken extends TokenType{
        public final Regex regex;
        private RegexToken(Regex regex) {
            this.regex = regex;
        }
    }
    private class AggregateToken extends TokenType{
        private final String parsationDataName;
        public ParsationData parsationData;
        public AggregateToken(String parsationDataName) {
            this.parsationDataName = parsationDataName;
            this.parsationData = null;
        }
        @Override
        public void compile() {
            if(this.parsationData != null)
                return;
            this.parsationData = parsationDataRegistry.get(parsationDataName);
            if(this.parsationData == null)
                throw new IllegalStateException("Parser compilation failed: aggregate " + parsationDataName + " not found");
        }
    }
    private class ChoiceToken extends TokenType{
        private final List<TokenType> choices;
        private boolean compiled;
        private ChoiceToken(List<TokenType> choices) {
            this.choices = choices;
            this.compiled = false;
        }
        @Override
        public void compile() {
            if(compiled)
                return;
            compiled = true;
            for(TokenType tokenType : choices)
                tokenType.compile();
        }
    }
}
