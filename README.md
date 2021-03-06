# RRFramework

- This is a framework for Android APP development. It has the following features:
  Readable
  Scalability
  Easy to use
  Welcome to join us together to polish it if you have some ideas!

# Table of contents

- [Status](#Status)
- [Project structure](#Project-structure)
- [Coding Styles](#Coding-Styles)
- [Community](#Community)
- [Copyright and license](#Copyright-and-license)

# Status

[![Build Status](https://travis-ci.org/rawray/RRFramework-Android.svg?branch=master)](https://travis-ci.org/rawray/RRFramework-Android)


# Project structure

## Take MVVM to design the main frame, the universal code has been included into this frame,  business and changes has been Stripped
- Transverse dividers （UI, Service, Business, Model, Uitls, IO, Vendor...)
- Vertical stratification （Interface layer， Abstract layer, Base layer, Implement layer）

## UI
- Activity/Fragemnt vertical stratification
- Activity/Fragment status switch support（Loading, Content, Empty data, Network error, Other error）
- Pull refresh and loadmore support （Swipe， Custom）

## Service
Pure service, it has nothing to do with business
- Management of data
- Management of processes and thread
- Thread queue management
- Event bus

## Business
Universal Business
- Sign up & Log in
- Share
- Data reporting
- New version update
 
## Model
Data Model

## IO
Data of CRUD
- Adapter
- Cache(Memory, File)
- File
- Database
- Network

# Coding Styles
- Use dividing line to distinguish Java code and pls follow the order of queue
```java
    //----------------------- Absolute Methed ---------------------------------

    //----------------------- Override Methed ---------------------------------

    //----------------------- Static Methed -----------------------------------

    //----------------------- Public Methed -----------------------------------

    //----------------------- Protected Methed --------------------------------

    //----------------------- Private Methed ----------------------------------
```    
- Override method from base class to implement class, for example:
```java
    //----------------------- AbsFragment -------------------------------------
    @Override
    public void initContentView(View view) {
        super.initContentView(view);
        loadData();
    }

    //----------------------- AbsStatusFragment -------------------------------
    @Override
    protected void initStatusLayout() {
        super.initStatusLayout();
    }

    //----------------------- AbsRecyclerStatusFragment -----------------------
    @Override
    protected AbsRecyclerAdapter onCreateRecyclerAdapter() {
        return super.onCreateRecyclerAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return super.onCreateLayoutManager();
    }

    //----------------------- AbsSwipeRecyclerStatusFragment -----------------------
    @Override
    public void onPullRefresh() {
           //TODO
    }

    @Override
    public void onLoadMore() {
           //TODO
    }
```


# Community
Get updates on RRFramework's development and subscribe personal blog on wechat.

## Personal blog on wechat
- Search "RRFramework" in personal blog on wechat
- scan the qrcode below to subscribe

![qrcode of personal blog on wechat](./doc/images/qrcode_for_wechat.jpg)

## Homepage & Email
- http://www.rawray.com & du_guo@sina.com


# Copyright and license

Code released under the [MIT License]
