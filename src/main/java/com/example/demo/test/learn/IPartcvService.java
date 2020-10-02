package com.example.demo.test.learn;

import org.springframework.stereotype.Service;

//Example
public interface IPartcvService {}
@Service
class PartcvService implements IPartcvService{}//or
@Service
class PartcvServiceImpl implements IPartcvService{}
// my QA
//So I want to develop another implementation,so what would be name of class?

interface partcv1Service{

}
class Partcv1ServiceImpl implements partcv1Service{

}
