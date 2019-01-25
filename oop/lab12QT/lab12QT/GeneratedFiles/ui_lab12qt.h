/********************************************************************************
** Form generated from reading UI file 'lab12qt.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LAB12QT_H
#define UI_LAB12QT_H

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

class Ui_lab12QTClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *lab12QTClass)
    {
        if (lab12QTClass->objectName().isEmpty())
            lab12QTClass->setObjectName(QStringLiteral("lab12QTClass"));
        lab12QTClass->resize(600, 400);
        menuBar = new QMenuBar(lab12QTClass);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        lab12QTClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(lab12QTClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        lab12QTClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(lab12QTClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        lab12QTClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(lab12QTClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        lab12QTClass->setStatusBar(statusBar);

        retranslateUi(lab12QTClass);

        QMetaObject::connectSlotsByName(lab12QTClass);
    } // setupUi

    void retranslateUi(QMainWindow *lab12QTClass)
    {
        lab12QTClass->setWindowTitle(QApplication::translate("lab12QTClass", "lab12QT", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class lab12QTClass: public Ui_lab12QTClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LAB12QT_H
