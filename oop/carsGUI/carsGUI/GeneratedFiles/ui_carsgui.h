/********************************************************************************
** Form generated from reading UI file 'carsgui.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CARSGUI_H
#define UI_CARSGUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_carsGUIClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *carsGUIClass)
    {
        if (carsGUIClass->objectName().isEmpty())
            carsGUIClass->setObjectName(QStringLiteral("carsGUIClass"));
        carsGUIClass->resize(600, 400);
        menuBar = new QMenuBar(carsGUIClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        carsGUIClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(carsGUIClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        carsGUIClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(carsGUIClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        carsGUIClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(carsGUIClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        carsGUIClass->setStatusBar(statusBar);

        retranslateUi(carsGUIClass);

        QMetaObject::connectSlotsByName(carsGUIClass);
    } // setupUi

    void retranslateUi(QMainWindow *carsGUIClass)
    {
        carsGUIClass->setWindowTitle(QApplication::translate("carsGUIClass", "carsGUI", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class carsGUIClass: public Ui_carsGUIClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CARSGUI_H
